package com.sbnz.recovery.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
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
	private KnowledgeSessionService knowledgeSessionService;
	
	public Therapy createTherapy(Therapy therapy) {
		return therapyRepository.save(therapy);
	}
	
	public Ingredient createIngredient(Ingredient ingredient) {
		return ingredientRepository.save(ingredient);
	}
	
	public Meal createMeal(Meal meal) {
		return mealRepository.save(meal);
	}
	
	public List<Meal> findAllAvailableMeals(PatientDTO patientDto) throws Exception{
		Patient patient = patientService.findOneByUsername(patientDto.getEmail());
		if(patient == null) {
			throw new Exception();
		}
		List<Meal> meals = mealRepository.findAll();
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		List<Meal> patientMeals = new ArrayList<Meal>();
		kieSession.getAgenda().getAgendaGroup("rank-meal").setFocus();
		kieSession.setGlobal("chosenPatientUsername", patient.getUsername());
		kieSession.setGlobal("mealList", patientMeals);
		for (Meal meal : meals) {
			kieSession.insert(meal);
		}
		kieSession.insert(patient);
		kieSession.fireAllRules();
		return patientMeals;
	}
	
	public List<AppliedTherapy> findAllAvailableTherapies(PatientDTO patientDto) throws Exception{
		Patient patient = patientService.findOneByUsername(patientDto.getEmail());
		if(patient == null) {
			throw new Exception();
		}
		List<Therapy> therapies = therapyRepository.findAll();
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		kieSession.setGlobal("chosenPatientUsername", patient.getUsername());
		kieSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
		for (Therapy therapy : therapies) {
			kieSession.insert(therapy);
		}
		kieSession.insert(patient);
		kieSession.fireAllRules();
		return atRepository.findAllByInjuryPatientUsername(patient.getUsername());
	}
	
	public Meal addMeal(Long patientId, Meal meal) {
		Patient patient = patientService.findOneById(patientId);
		if(patient == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient doesn't exist.");
		}
		meal = mealRepository.save(meal);
//		patient.addMeal(meal);
		patientService.save(patient);
		return meal;
	}
	
	public List<Patient> findPotentialAbuse(){
		List<Patient> patients = patientService.findAll();
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		List<Patient> patientReport = new ArrayList<Patient>();
		kieSession.setGlobal("patientReport", patientReport);
		kieSession.getAgenda().getAgendaGroup("abuse-report").setFocus();
		for (Patient patient : patients) {
			kieSession.insert(patient);
			kieSession.fireAllRules();
		}
		return patientReport;
	}
	
	public List<Patient> findPotentialAtrophy(){
		List<Patient> patients = patientService.findAll();
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		List<Patient> patientReport = new ArrayList<Patient>();
		kieSession.setGlobal("patientReport", patientReport);
		kieSession.getAgenda().getAgendaGroup("atrophy-report").setFocus();
		for (Patient patient : patients) {
			kieSession.insert(patient);
			kieSession.fireAllRules();
		}
		return patientReport;
	}
	
	public List<Patient> findPotentialEatingDisorder(){
		List<Patient> patients = patientService.findAll();
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		List<Patient> patientReport = new ArrayList<Patient>();
		kieSession.setGlobal("patientReport", patientReport);
		kieSession.getAgenda().getAgendaGroup("eating-report").setFocus();
		for (Patient patient : patients) {
			kieSession.insert(patient);
			kieSession.fireAllRules();
		}
		return patientReport;
	}
}
