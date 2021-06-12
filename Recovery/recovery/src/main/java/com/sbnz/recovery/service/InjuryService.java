package com.sbnz.recovery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.NonExistingIdException;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.repository.InjuryRepository;
import com.sbnz.recovery.repository.PatientRepository;

@Service
public class InjuryService {
	
	@Autowired
	private InjuryRepository injuryRepository;
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private KnowledgeSessionService kieSessionService;
	
	public List<Injury> findAll() {
		return injuryRepository.findAll();
	}
	
	public Injury addInjury(Patient patient, Injury injury) throws NonExistingIdException {
		patient = patientRepository.findById(patient.getId()).orElse(null);
		if (patient == null) {
			throw new NonExistingIdException("Patient");
		}
		patient.addInjury(injury);
		return injury;
	}
}
