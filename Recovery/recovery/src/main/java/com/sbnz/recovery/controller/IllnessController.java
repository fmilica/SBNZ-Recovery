package com.sbnz.recovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbnz.recovery.dto.IllnessDTO;
import com.sbnz.recovery.helper.IllnessMapper;
import com.sbnz.recovery.service.IllnessService;

@RestController
@RequestMapping(value = "api/illness", produces = MediaType.APPLICATION_JSON_VALUE)
public class IllnessController {
	
	private IllnessService illnessService;
	private final IllnessMapper illnessMapper;

	@Autowired
	public IllnessController(IllnessService illnessService) {
		this.illnessService = illnessService;
		this.illnessMapper = new IllnessMapper();
	}
	
	@GetMapping
	public ResponseEntity<List<IllnessDTO>> findAll() {
		return new ResponseEntity<List<IllnessDTO>>(illnessMapper.toDtoList(this.illnessService.findAll()), HttpStatus.OK);
	}
}
