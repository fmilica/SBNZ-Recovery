package com.sbnz.recovery.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.exceptions.NonExistingIdException;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.repository.PatientRepository;
import com.sbnz.recovery.repository.TherapyRepository;

@Service
public class TherapyService {

	@Autowired
	private TherapyRepository therapyRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	public List<Therapy> getAllTherapies() {
		return therapyRepository.findAll();
	}
	
	public Therapy createTherapy(Therapy therapy) throws ExistingFieldValueException, NonExistingIdException {
		Therapy existing = therapyRepository.findOneByName(therapy.getName());
		if (existing != null) {
			throw new ExistingFieldValueException("Therapy", "name");
		}
		rulesSession.getAgenda().getAgendaGroup("classify-therapy").setFocus();
		rulesSession.insert(therapy);
		rulesSession.fireAllRules();
		return therapyRepository.save(therapy);
	}
	
	public void assignTherapy(Long patientId) throws NonExistingIdException {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new NonExistingIdException("Patient");
		}
		rulesSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
		rulesSession.setGlobal("chosenPatientUsername", patient.getUsername());
		rulesSession.insert(patient);
		int fired = rulesSession.fireAllRules();
		System.out.println(fired);
		rulesSession.getAgenda().getAgendaGroup("rank-therapy").setFocus();
		fired = rulesSession.fireAllRules();
		System.out.println(fired);
		patientRepository.save(patient);
	}
}
