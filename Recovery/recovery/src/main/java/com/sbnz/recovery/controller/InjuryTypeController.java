package com.sbnz.recovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.recovery.dto.InjuryTypeDTO;
import com.sbnz.recovery.helper.InjuryTypeMapper;
import com.sbnz.recovery.service.InjuryTypeService;

@RestController
@RequestMapping(value = "api/injury-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class InjuryTypeController {

	private InjuryTypeService injuryTypeService;
	private final InjuryTypeMapper injuryTypeMapper;
	
	@Autowired
	public InjuryTypeController(InjuryTypeService injuryTypeService) {
		this.injuryTypeService = injuryTypeService;
		this.injuryTypeMapper = new InjuryTypeMapper();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_DOCTOR')")
	@GetMapping
	public ResponseEntity<List<InjuryTypeDTO>> findAll() {
		return new ResponseEntity<List<InjuryTypeDTO>>(injuryTypeMapper.toDtoList(this.injuryTypeService.findAll()), HttpStatus.OK);
	}
}
