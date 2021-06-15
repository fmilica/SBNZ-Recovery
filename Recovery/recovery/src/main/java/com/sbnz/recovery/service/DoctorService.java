package com.sbnz.recovery.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.DailyMeal;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.IngredientAmount;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.events.MealEvent;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
import com.sbnz.recovery.repository.DailyMealRepository;
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
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	@Autowired
	@Qualifier(value = "cepSession")
	private KieSession cepSession;
	
//	public Therapy createTherapy(Therapy therapy) {
//		return therapyRepository.save(therapy);
//	}
	
	public Ingredient createIngredient(Ingredient ingredient) {
		rulesSession.getAgenda().getAgendaGroup("classify-ingredient").setFocus();
		rulesSession.insert(ingredient);
		rulesSession.fireAllRules();
		return ingredientRepository.save(ingredient);
	}
	
	public List<Ingredient> findAllIngredients(){
		return ingredientRepository.findAll(); 
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
	
	public List<Meal> findAllMeals(){
		return mealRepository.findAll(); 
	}
	
	public List<Meal> findAllAvailableMeals(PatientDTO patientDto) throws Exception{
		Patient patient = patientService.findOneByUsername(patientDto.getEmail());
		if(patient == null) {
			throw new Exception();
		}
		List<Meal> meals = mealRepository.findAll();
		List<Meal> patientMeals = new ArrayList<Meal>();
		rulesSession.getAgenda().getAgendaGroup("rank-meal").setFocus();
		rulesSession.setGlobal("chosenPatientUsername", patient.getUsername());
		rulesSession.setGlobal("mealList", patientMeals);
		for (Meal meal : meals) {
			rulesSession.insert(meal);
		}
		rulesSession.insert(patient);
		rulesSession.fireAllRules();
		return patientMeals;
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
	
	public Meal addMeal(Long patientId, MealDTO mealDto) {
		Patient patient = patientService.findOneById(patientId);
		if(patient == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient doesn't exist.");
		}
		cepSession.insert(patient);
		Meal meal = mealRepository.findOneById(mealDto.getId());
		if(meal == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meal doesn't exist.");
		}
		MealEvent mealEvent = new MealEvent(patient.getUsername(), meal.getTotalCalories());
		cepSession.insert(mealEvent);
		int firedRules = cepSession.fireAllRules();
		if(firedRules > 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calories overflow.");
		}else {
			DailyMeal dailyMeal = dailyMealRepository.findOneByPatientId(patientId);
			if(dailyMeal == null) {
				List<Meal> meals = new ArrayList<Meal>();
				meals.add(meal);
				dailyMeal = dailyMealRepository.save(new DailyMeal(new Date(), meals));
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
	
	public List<Patient> findPotentialAbuse(){
		List<Patient> patients = patientService.findAll();
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("abuse-report").setFocus();
		for (Patient patient : patients) {
			rulesSession.insert(patient);
			rulesSession.fireAllRules();
		}
		return patientReport;
	}
	
	public List<Patient> findPotentialAtrophy(){
		List<Patient> patients = patientService.findAll();
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("atrophy-report").setFocus();
		for (Patient patient : patients) {
			rulesSession.insert(patient);
			rulesSession.fireAllRules();
		}
		return patientReport;
	}
	
	public List<Patient> findPotentialEatingDisorder(){
		List<Patient> patients = patientService.findAll();
		List<Patient> patientReport = new ArrayList<Patient>();
		rulesSession.setGlobal("patientReport", patientReport);
		rulesSession.getAgenda().getAgendaGroup("eating-report").setFocus();
		for (Patient patient : patients) {
			rulesSession.insert(patient);
			rulesSession.fireAllRules();
		}
		return patientReport;
	}
}
