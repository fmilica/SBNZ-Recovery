package com.sbnz.recovery.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeExclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.AppliedTherapyDTO;
import com.sbnz.recovery.dto.IngredientDTO;
import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.helper.AppliedTherapyMapper;
import com.sbnz.recovery.helper.IngredientMapper;
import com.sbnz.recovery.helper.MealMapper;
import com.sbnz.recovery.helper.PatientMapper;
import com.sbnz.recovery.helper.TherapyMapper;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
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

	@Autowired
	public DoctorController(DoctorService doctorService, PatientService patientService) {
		this.doctorService = doctorService;
		this.theraphyMapper = new TherapyMapper();
		this.ingredientMapper = new IngredientMapper();
		this.mealMapper = new MealMapper();
		this.patientMapper = new PatientMapper();
		this.appliedTherapyMapper = new AppliedTherapyMapper();
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping("/create-therapy")
	public ResponseEntity<TherapyDTO> createTherapy(@RequestBody TherapyDTO therapyDto) {

		Therapy therapy = theraphyMapper.toEntity(therapyDto);

		log.debug("Create therapy: " + therapy);
		
		
		try {
            therapy = doctorService.createTherapy(therapy);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<TherapyDTO>(theraphyMapper.toDto(therapy), HttpStatus.OK);
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
	@GetMapping("/patient-rank-therapies")
	public ResponseEntity<List<AppliedTherapyDTO>> getPatientTherapies(@RequestBody PatientDTO patientDto) {


		log.debug("List available therapies for patient : " + patientDto.getUsername());
		
		List<AppliedTherapy> appliedTherapies = new ArrayList<AppliedTherapy>();
		try {
			appliedTherapies = doctorService.findAllAvailableTherapies(patientDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<AppliedTherapyDTO>>(appliedTherapyMapper.toDtoList(appliedTherapies), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/patient-rank-meals")
	public ResponseEntity<List<MealDTO>> getPatientMeals(@RequestBody PatientDTO patientDto) {

		log.debug("List available meals for patient : " + patientDto.getUsername());
		
		List<Meal> availableMeals = new ArrayList<Meal>();
		
		try {
            availableMeals = doctorService.findAllAvailableMeals(patientDto);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<MealDTO>>(mealMapper.toDtoList(availableMeals), HttpStatus.OK);
	}
	

	//mozda i ovo treba
	//@PreAuthorize("hasRole('ROLE_DOCTOR')")
	/*@PostMapping("/{patient-id}/add-therapy")
	public ResponseEntity<TherapyDTO> addTherapy(@PathVariable("patient-id") Long patientId, 
			@RequestBody TherapyDTO therapyDto) {
		
		Therapy therapy = therapyMapper.toEntity(therapyDto);

		log.debug("Add therapy: " + therapy);
		
		try {
            therapy = doctorService.addTherapy(patientId, therapy);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<TherapyDTO>(therapyMapper.toDto(therapy), HttpStatus.OK);
	}*/
	
	//kasnije rekla eva?
	//@PreAuthorize("hasRole('ROLE_DOCTOR')")
	/*@PostMapping("/{patient-id}/add-meal")
	public ResponseEntity<MealDTO> addMeal(@PathVariable("patient-id") Long patientId, 
			@RequestBody MealDTO mealDto) {
		
		Meal meal = mealMapper.toEntity(mealDto);

		log.debug("Add meal: " + meal);
		
		try {
            meal = doctorService.addMeal(patientId, meal);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<MealDTO>(mealMapper.toDto(meal), HttpStatus.OK);
	}*/
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/potential-abuse")
	public ResponseEntity<List<PatientDTO>> getPotentialAbuse() {

		log.debug("List patients with potential abuse.");
		
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

		log.debug("List patients with potential atrophy.");
		
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

		log.debug("List patients with potential eating disorder.");
		
		List<Patient> patients = new ArrayList<Patient>();
		try {
            patients = doctorService.findPotentialEatingDisorder();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

		return new ResponseEntity<List<PatientDTO>>(patientMapper.toDtoList(patients), HttpStatus.OK);
	}
}
