package com.sbnz.recovery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.service.TestService;

@RestController
@RequestMapping(value = "api/integration-test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

	private static Logger log = LoggerFactory.getLogger(TestController.class);
	
	private final TestService testService;
	
	@Autowired
	public TestController(TestService testService) {
		this.testService = testService;
	}
	
	@PostMapping
	public ResponseEntity<Patient> getQuestions(@RequestBody PatientDTO patientDto) {

		Patient newPatient = new Patient(patientDto.getUsername(), patientDto.getPassword(), patientDto.getName(), patientDto.getSurname(),
				patientDto.getGender(), patientDto.getDateOfBirth(), patientDto.getHeight(), patientDto.getWeight(), patientDto.getPhysicalActivity(),
				patientDto.getAnamnesis());

		log.debug("Test request received for patient: " + newPatient);
		
		Patient changed = testService.calculateBmi(newPatient);

		return new ResponseEntity<Patient>(changed, HttpStatus.OK);
	}
}
