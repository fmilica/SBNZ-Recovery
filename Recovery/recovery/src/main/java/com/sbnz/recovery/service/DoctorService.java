package com.sbnz.recovery.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.dto.InjuryCountDTO;
import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.exceptions.NonExistingIdException;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.ChosenPatient;
import com.sbnz.recovery.model.DailyMeal;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.IngredientAmount;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Report;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.enums.AssignType;
import com.sbnz.recovery.model.events.MealEvent;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
import com.sbnz.recovery.repository.DailyMealRepository;
import com.sbnz.recovery.repository.IllnessRepository;
import com.sbnz.recovery.repository.IngredientAmountRepository;
import com.sbnz.recovery.repository.IngredientRepository;
import com.sbnz.recovery.repository.MealRepository;
import com.sbnz.recovery.repository.PatientRepository;
import com.sbnz.recovery.repository.TherapyRepository;

@Service
public class DoctorService {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private TherapyRepository therapyRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private AppliedTherapyRepository atRepository;
	
	@Autowired
	private IngredientAmountRepository ingredientAmountRepository;
	
	@Autowired
	private DailyMealRepository dailyMealRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private IllnessRepository illnessRepository;

	@Autowired
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	@Autowired
	@Qualifier(value = "cepSession")
	private KieSession cepSession;
	
	
	public Ingredient createIngredient(Ingredient ingredient) {
		rulesSession.getAgenda().getAgendaGroup("classify-ingredient").setFocus();
		rulesSession.insert(ingredient);
		rulesSession.fireAllRules();
		return ingredientRepository.save(ingredient);
	}
	
	public Meal createMeal(Meal meal) throws Exception {
		for (IngredientAmount ia : meal.getIngredients()) {
			Ingredient i = ingredientRepository.findOneById(ia.getIngredient().getId());
			if(i == null) {
				throw new Exception();
			}
			ia.setIngredient(i);
			ia = ingredientAmountRepository.save(ia);
		}
		rulesSession.getAgenda().getAgendaGroup("meal").setFocus();
		rulesSession.insert(meal);
		rulesSession.fireAllRules();
		return mealRepository.save(meal);
	}
	
	public List<Meal> findAllAvailableMeals(String patientUsername) throws Exception{
		Patient patient = patientService.findOneByUsername(patientUsername);
		if(patient == null) {
			throw new Exception();
		}
		QueryResults results = rulesSession.getQueryResults("getAllPatients");
		List<Patient> patients1 = new ArrayList<>();
		for (QueryResultsRow row : results) {
			Patient patient1 = (Patient) row.get("$patient");
			patients1.add(patient1);
		}
		QueryResults results1 = rulesSession.getQueryResults("getAllMeals");
		List<Meal> meals1 = new ArrayList<>();
		for (QueryResultsRow row : results1) {
			Meal meal1 = (Meal) row.get("$meal");
			meals1.add(meal1);
		}
		
		List<Meal> patientMeals = new ArrayList<Meal>();
		rulesSession.getAgenda().getAgendaGroup("rank-meal").setFocus();
		rulesSession.setGlobal("mealList", patientMeals);
		rulesSession.insert(new ChosenPatient(patient.getId(), AssignType.MEAL));
		rulesSession.fireAllRules();
		
		return patientMeals;
		
//		rulesSession.getAgenda().getAgendaGroup("test").setFocus();
//		rulesSession.insert(new ChosenPatient(patient.getId()));
//		rulesSession.fireAllRules();
//		return new ArrayList<Meal>();
	}
	
	public List<AppliedTherapy> findAllAvailableTherapies(PatientDTO patientDto) throws Exception{
		Patient patient = patientService.findOneByUsername(patientDto.getEmail());
		if(patient == null) {
			throw new Exception();
		}
		List<Therapy> therapies = therapyRepository.findAll();
		rulesSession.setGlobal("chosenPatientUsername", patient.getUsername());
		rulesSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
		for (Therapy therapy : therapies) {
			rulesSession.insert(therapy);
		}
		rulesSession.insert(patient);
		rulesSession.fireAllRules();
		return atRepository.findAllByInjuryPatientUsername(patient.getUsername());
	}
	
	public Meal addMeal(Long patientId, MealDTO mealDto) throws Exception {
		Patient patient = patientService.findOneById(patientId);
		if(patient == null) {
			throw new NonExistingIdException("Patient");
		}
		cepSession.insert(patient);
		Meal meal = mealRepository.findOneById(mealDto.getId());
		if(meal == null) {
			throw new NonExistingIdException("Meal");
		}
		DailyMeal dailyMeal = dailyMealRepository.findOneByPatientIdAndDayAndMealsId(patientId, new Date(), mealDto.getId());
		if(dailyMeal != null) {
			throw new ExistingFieldValueException("DailyMeal", "Meal");
		}
		MealEvent mealEvent = new MealEvent(patient.getUsername(), meal.getTotalCalories());
		FactHandle handle =  cepSession.insert(mealEvent);
		int firedRules = cepSession.fireAllRules();
		if(firedRules > 0) {
			cepSession.delete(handle);
			throw new Exception("Calories overflow.");
		}else {
			dailyMeal = dailyMealRepository.findOneByPatientIdAndDay(patientId, new Date());
			if(dailyMeal == null) {
				Set<Meal> meals = new HashSet<Meal>();
				meals.add(meal);
				dailyMeal = new DailyMeal(new Date(), meals);
				dailyMeal.setPatient(patient);
				dailyMeal = dailyMealRepository.save(dailyMeal);
				patient.addDailyMeal(dailyMeal);
				patientRepository.save(patient);
			}
			else {
				dailyMeal.addMeal(meal);
				dailyMeal = dailyMealRepository.save(dailyMeal);
			}
		}
		return meal;
	}
	
	public DailyMeal findAllDailyMeals(Long patientId) {
		return dailyMealRepository.findOneByPatientIdAndDay(patientId, new Date());
	}
	
  // reports
	public List<Patient> findPotentialAbuse() {
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("abuse-report").setFocus();
		rulesSession.insert(new Report());
		rulesSession.fireAllRules();
		return patientReport;
	}
	
	public List<Patient> findPotentialAtrophy() {
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("atrophy-report").setFocus();
		rulesSession.insert(new Report());
		rulesSession.fireAllRules();
		return patientReport;
	}
	
	public List<Patient> findPotentialEatingDisorder() {
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("eating-report").setFocus();
		rulesSession.insert(new Report());
		rulesSession.fireAllRules();
		return patientReport;
	}
	
	public int findInjuryCountByAgeGroupAndInterval(InjuryCountDTO countByAge) {
		QueryResults results = 
				rulesSession.getQueryResults("getInjuriesCountByAgeGroup", 
						countByAge.getStartAge(), countByAge.getEndAge(), 
						countByAge.getStartDate(), countByAge.getEndDate());
		int total = 0;
		for (QueryResultsRow row : results) {
			total = ((Number)row.get("$totalInjuriesInAgeGroup")).intValue();
		}
		return total;
	}
	
	public int findInjuryCountByInjuryTypeAndInterval(InjuryCountDTO countByType) {
		QueryResults results = 
				rulesSession.getQueryResults("getTotalInjuriesCountByInjuryType", 
						countByType.getInjuryTypeId(), 
						countByType.getStartDate(), countByType.getEndDate());
		int total = 0;
		for (QueryResultsRow row : results) {
			total = ((Number)row.get("$totalInjuriesForInjuryType")).intValue();
		}
		return total;
	}
	
	// filter
	public List<Ingredient> filterIngredients(Long illnessId) throws NonExistingIdException {
		Illness illness = illnessRepository.findById(illnessId).orElse(null);
		if (illness == null) {
			throw new NonExistingIdException("Illness");
		}
		QueryResults results = rulesSession.getQueryResults("getAllIngredientsByIllness", illness);
		List<Ingredient> ingredients = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Ingredient ingredient = (Ingredient) row.get("$ingredient");
			ingredients.add(ingredient);
		}
		return ingredients;
	}
	
	public List<Meal> filterMeals(Long illnessId) throws NonExistingIdException {
		Illness illness = illnessRepository.findById(illnessId).orElse(null);
		if (illness == null) {
			throw new NonExistingIdException("Illness");
		}
		QueryResults results = rulesSession.getQueryResults("getAllMealsByIllness", illness);
		List<Meal> meals = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			meals = (List<Meal>) row.get("$meals");
		}
		return meals;
	}
	
	// find all iz sesije
	public List<Ingredient> findAllIngredients(){
		QueryResults results = rulesSession.getQueryResults("getAllIngredients");
		List<Ingredient> ingredients = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Ingredient ingredient = (Ingredient) row.get("$ingredient");
			ingredients.add(ingredient);
		}
		return ingredients;
		//return ingredientRepository.findAll(); 
	}
	
	public List<Meal> findAllMeals(){
		QueryResults results = rulesSession.getQueryResults("getAllMeals");
		List<Meal> meals = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Meal meal = (Meal) row.get("$meal");
			meals.add(meal);
		}
		return meals;
		//return mealRepository.findAll(); 
	}
	
}
