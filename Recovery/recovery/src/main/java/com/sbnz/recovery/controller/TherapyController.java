package com.sbnz.recovery.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.AppliedTherapyDTO;
import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.dto.ViewTherapyDTO;
import com.sbnz.recovery.helper.AppliedTherapyMapper;
import com.sbnz.recovery.helper.TherapyMapper;
import com.sbnz.recovery.helper.ViewTherapyMapper;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.service.TherapyService;

@RestController
@RequestMapping(value = "api/therapy", produces = MediaType.APPLICATION_JSON_VALUE)
public class TherapyController {
	
	private TherapyService therapyService;
	private final TherapyMapper therapyMapper;
	private final ViewTherapyMapper viewTherapyMapper;
	private final AppliedTherapyMapper appliedTherapyMapper;
	
	@Autowired
	public TherapyController(TherapyService therapyService) {
		this.therapyService = therapyService;
		this.therapyMapper = new TherapyMapper();
		this.viewTherapyMapper = new ViewTherapyMapper();
		this.appliedTherapyMapper = new AppliedTherapyMapper();
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping
	public ResponseEntity<List<ViewTherapyDTO>> getAllTherapies() {
        List<Therapy> therapies;
		try {
			therapies = therapyService.getAllTherapies();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(viewTherapyMapper.toDtoList(therapies), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping(value = "/filter")
	public ResponseEntity<List<ViewTherapyDTO>> filterTherapies(
			@RequestParam(name = "illnessId") Long illnessId,
			@RequestParam(name = "injuryTypeId") Long injuryTypeId) {
        List<Therapy> therapies;
		try {
			therapies = therapyService.filterTherapiesByIllness(illnessId, injuryTypeId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(viewTherapyMapper.toDtoList(therapies), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/current/daily-applied")
	public ResponseEntity<List<AppliedTherapyDTO>> filterTherapies() {
        List<AppliedTherapy> appliedTherapies;
		try {
            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			appliedTherapies = therapyService.findAppliedForCurrent(patient.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(appliedTherapyMapper.toDtoList(appliedTherapies), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@PostMapping
	public ResponseEntity<TherapyDTO> createTherapy(@RequestBody TherapyDTO therapyDto) {
		Therapy therapy = therapyMapper.toEntity(therapyDto);
        try {
        	therapy = therapyService.createTherapy(therapy);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(therapyMapper.toDto(therapy), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_DOCTOR')")
	@GetMapping(value = "/assign/{patientId}")
	public ResponseEntity<Void> assignTherapy(@PathVariable("patientId") Long patientId) {
        boolean success;
		try {
        	success = therapyService.assignTherapy(patientId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
		if (success) {
	        return new ResponseEntity<>(HttpStatus.OK);
		}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
