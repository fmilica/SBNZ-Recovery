package com.sbnz.recovery.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.AppliedTherapyDTO;
import com.sbnz.recovery.dto.DailyMealDTO;
import com.sbnz.recovery.dto.IngredientDTO;
import com.sbnz.recovery.dto.InjuryCountDTO;
import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.helper.AppliedTherapyMapper;
import com.sbnz.recovery.helper.DailyMealMapper;
import com.sbnz.recovery.helper.IngredientMapper;
import com.sbnz.recovery.helper.MealMapper;
import com.sbnz.recovery.helper.PatientMapper;
import com.sbnz.recovery.helper.TherapyMapper;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.DailyMeal;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.service.DoctorService;
import com.sbnz.recovery.service.PatientService;

@RestController
@RequestMapping(value = "api/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorController {
	
	private static Logger log = LoggerFactory.getLogger(DoctorController.class);
	
	private DoctorService doctorService;
	
	private final TherapyMapper theraphyMapper;
	private final IngredientMapper ingredientMapper;
	private final MealMapper mealMapper;
	private final PatientMapper patientMapper;
	private final AppliedTherapyMapper appliedTherapyMapper;
	private final DailyMealMapper dailyMealMapper;

	@Autowired
	public DoctorController(DoctorService doctorService, PatientService patientService) {
		this.doctorService = doctorService;
		this.theraphyMapper = new TherapyMapper();
		this.ingredientMapper = new IngredientMapper();
		this.mealMapper = new MealMapper();
		this.patientMapper = new PatientMapper();
		this.appliedTherapyMapper = new AppliedTherapyMapper();
		this.dailyMealMapper = new DailyMealMapper();
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/create-ingredient")
	public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDto) {

		Ingredient ingredient = ingredientMapper.toEntity(ingredientDto);

		log.debug("Create ingredient: " + ingredient);
		
		
		try {
            ingredient = doctorService.createIngredient(ingredient);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<IngredientDTO>(ingredientMapper.toDto(ingredient), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/get-ingredients")
	public ResponseEntity<List<IngredientDTO>> getAllIngredients() {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		log.debug("Get all ingredients: ");
		
		try {
            ingredients = doctorService.findAllIngredients();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<List<IngredientDTO>>(ingredientMapper.toDtoList(ingredients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/filter-ingredients/{illnessId}")
	public ResponseEntity<List<IngredientDTO>> filterIngredients(@PathVariable("illnessId") Long illnessId) {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		try {
            ingredients = doctorService.filterIngredients(illnessId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<List<IngredientDTO>>(ingredientMapper.toDtoList(ingredients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/create-meal")
	public ResponseEntity<MealDTO> createMeal(@RequestBody MealDTO mealDto) {

		Meal meal = mealMapper.toEntity(mealDto);

		log.debug("Create meal: " + meal);
		
		try {
            meal = doctorService.createMeal(meal);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<MealDTO>(mealMapper.toDto(meal), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/get-meals")
	public ResponseEntity<List<MealDTO>> getAllMeals() {
		List<Meal> meals = new ArrayList<Meal>();
		log.debug("Get all meals: ");
		
		try {
			meals = doctorService.findAllMeals();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<List<MealDTO>>(mealMapper.toDtoList(meals), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/filter-meals/{illnessId}")
	public ResponseEntity<List<MealDTO>> filterMeals(@PathVariable("illnessId") Long illnessId) {
		List<Meal> meals = new ArrayList<Meal>();
		try {
			meals = doctorService.filterMeals(illnessId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<List<MealDTO>>(mealMapper.toDtoList(meals), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/patient-rank-therapies")
	public ResponseEntity<List<AppliedTherapyDTO>> getPatientTherapies(@RequestBody PatientDTO patientDto) {


		log.debug("List available therapies for patient : " + patientDto.getEmail());
		
		List<AppliedTherapy> appliedTherapies = new ArrayList<AppliedTherapy>();
		try {
			appliedTherapies = doctorService.findAllAvailableTherapies(patientDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<AppliedTherapyDTO>>(appliedTherapyMapper.toDtoList(appliedTherapies), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/patient-rank-meals/{patient-username}")
	public ResponseEntity<List<MealDTO>> getPatientMeals(@PathVariable("patient-username") String patientUsername) {

		log.debug("List available meals for patient : " + patientUsername);
		
		List<Meal> availableMeals = new ArrayList<Meal>();
		
		try {
            availableMeals = doctorService.findAllAvailableMeals(patientUsername);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<MealDTO>>(mealMapper.toDtoList(availableMeals), HttpStatus.OK);
	}
	

	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/{patient-id}/add-meal")
	public ResponseEntity<MealDTO> addMeal(@PathVariable("patient-id") Long patientId, 
			@RequestBody MealDTO mealDto) {
		
		Meal meal = null;

		log.debug("Add meal: " + mealDto.getId() + " to patient: " + patientId);
		
		try {
            meal = doctorService.addMeal(patientId, mealDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<MealDTO>(mealMapper.toDto(meal), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/{patient-id}/get-daily-meals")
	public ResponseEntity<DailyMealDTO> getAllDailyMeals(@PathVariable("patient-id") Long patientId) {
		DailyMeal meals = new DailyMeal();
		log.debug("Get all daily meals: ");
		
		try {
			meals = doctorService.findAllDailyMeals(patientId);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<DailyMealDTO>(dailyMealMapper.toDto(meals), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping("/get-current-daily-meals")
	public ResponseEntity<DailyMealDTO> getAllDailyMealsCurrent() {
		DailyMeal meals = new DailyMeal();
        Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		try {
			meals = doctorService.findAllDailyMeals(patient.getId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<DailyMealDTO>(dailyMealMapper.toDto(meals), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/potential-abuse")
	public ResponseEntity<List<PatientDTO>> getPotentialAbuse() {

		log.info("List patients with potential abuse.");
		
		List<Patient> patients = new ArrayList<Patient>();
		try {
            patients = doctorService.findPotentialAbuse();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<PatientDTO>>(patientMapper.toDtoList(patients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/potential-atrophy")
	public ResponseEntity<List<PatientDTO>> getPotentialAtrophy() {

		log.info("List patients with potential atrophy.");
		
		List<Patient> patients = new ArrayList<Patient>();
		try {
            patients = doctorService.findPotentialAtrophy();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<PatientDTO>>(patientMapper.toDtoList(patients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/potential-eating-disorder")
	public ResponseEntity<List<PatientDTO>> getPotentialEatingDisorder() {

		log.info("List patients with potential eating disorder.");
		
		List<Patient> patients = new ArrayList<Patient>();
		try {
            patients = doctorService.findPotentialEatingDisorder();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<PatientDTO>>(patientMapper.toDtoList(patients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/injury-count-by-age")
	public ResponseEntity<Integer> getInjuryCountByAge(@RequestBody InjuryCountDTO countByAgeDto) {
		int total = 0;
		try {
            total = doctorService.findInjuryCountByAgeGroupAndInterval(countByAgeDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<Integer>(total, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/injury-count-by-type")
	public ResponseEntity<Integer> getInjuryCountByType(@RequestBody InjuryCountDTO countByTypeDto) {
		int total = 0;
		try {
			total = doctorService.findInjuryCountByInjuryTypeAndInterval(countByTypeDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<Integer>(total, HttpStatus.OK);
	}
}
