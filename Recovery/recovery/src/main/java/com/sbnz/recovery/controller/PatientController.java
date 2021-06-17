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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.helper.PatientMapper;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.service.PatientService;

@RestController
@RequestMapping(value = "api/patient", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

private static Logger log = LoggerFactory.getLogger(DoctorController.class);
	
	private PatientService patientService;
	
	private final PatientMapper patientMapper;
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
		this.patientMapper = new PatientMapper();
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping("/get-patients")
	public ResponseEntity<List<PatientDTO>> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		log.debug("Get all patients: ");
		
		try {
			patients = patientService.findAll();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<List<PatientDTO>>(patientMapper.toDtoList(patients), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping("/get-details")
	public ResponseEntity<PatientDTO> getPatientDetails() {
		Patient patient = new Patient();
		log.debug("Get current patient: ");
		
		try {
			patient = patientService.findCurrentPatient();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		return new ResponseEntity<PatientDTO>(patientMapper.toDto(patient), HttpStatus.OK);
	}
}
