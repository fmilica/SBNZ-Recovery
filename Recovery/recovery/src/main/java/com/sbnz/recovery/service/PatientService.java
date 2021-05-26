package com.sbnz.recovery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired PatientRepository patientRepository;

	@Autowired
	private KnowledgeSessionService knowledgeSessionService;
	
	public Patient findOneById(Long id) {
		return patientRepository.findById(id).orElse(null);
	}
	
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}
	
	public Patient save(Patient p) {
		return patientRepository.save(p);
	}
	
	public Patient findOneByUsername(String username) {
		return patientRepository.findOneByUsername(username);
	}
}
