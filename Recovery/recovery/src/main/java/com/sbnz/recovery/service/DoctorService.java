package com.sbnz.recovery.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.IngredientAmount;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
import com.sbnz.recovery.repository.IngredientAmountRepository;
import com.sbnz.recovery.repository.IngredientRepository;
import com.sbnz.recovery.repository.MealRepository;
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
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
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
	
	public Meal addMeal(Long patientId, Meal meal) {
		Patient patient = patientService.findOneById(patientId);
		if(patient == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient doesn't exist.");
		}
		meal = mealRepository.save(meal);
//		patient.addMeal(meal);
		//mealEvent i dodaje se u cep sesiju, napravi DailyMeal instancu
		patientService.save(patient);
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
