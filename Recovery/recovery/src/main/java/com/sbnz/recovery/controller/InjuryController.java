package com.sbnz.recovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.InjuryDTO;
import com.sbnz.recovery.helper.InjuryMapper;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.service.InjuryService;

@RestController
@RequestMapping(value = "api/injury", produces = MediaType.APPLICATION_JSON_VALUE)
public class InjuryController {

	private InjuryService injuryService;
	private final InjuryMapper injuryMapper;
	
	@Autowired
	public InjuryController(InjuryService injuryService) {
		this.injuryService = injuryService;
		this.injuryMapper = new InjuryMapper();
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@GetMapping(value = "/current")
	public ResponseEntity<List<InjuryDTO>> findAllForCurrent() {
		List<Injury> injuries = null;
        try {
            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            injuries = injuryService.findAllForPatient(patient.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(injuryMapper.toDtoList(injuries), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@PostMapping
	public ResponseEntity<InjuryDTO> addInjury(@RequestBody InjuryDTO injuryDto) {
		Injury injury = injuryMapper.toEntity(injuryDto);
        try {
            Patient patient = (Patient) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            injury = injuryService.addInjury(patient, injury);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return new ResponseEntity<>(injuryMapper.toDto(injury), HttpStatus.OK);
	}
}
