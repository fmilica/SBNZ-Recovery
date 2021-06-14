package com.sbnz.recovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.helper.TherapyMapper;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.service.TherapyService;

@RestController
@RequestMapping(value = "api/therapy", produces = MediaType.APPLICATION_JSON_VALUE)
public class TherapyController {
	
	private TherapyService therapyService;
	private final TherapyMapper therapyMapper;
	
	@Autowired
	public TherapyController(TherapyService therapyService) {
		this.therapyService = therapyService;
		this.therapyMapper = new TherapyMapper();
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
}
