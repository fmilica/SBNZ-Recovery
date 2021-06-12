package com.sbnz.recovery.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.NonExistingIdException;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.repository.InjuryRepository;
import com.sbnz.recovery.repository.InjuryTypeRepository;
import com.sbnz.recovery.repository.PatientRepository;

@Service
public class InjuryService {
	
	@Autowired
	private InjuryRepository injuryRepository;
	
	@Autowired
	private InjuryTypeRepository injuryTypeRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	
	public List<Injury> findAll() {
		return injuryRepository.findAll();
	}
	
	public Injury addInjury(Patient patient, Injury injury) throws NonExistingIdException {
		patient = patientRepository.findById(patient.getId()).orElse(null);
		if (patient == null) {
			throw new NonExistingIdException("Patient");
		}
		InjuryType injuryType = injuryTypeRepository.findById(injury.getInjuryType().getId()).orElse(null);
		if (injuryType == null) {
			throw new NonExistingIdException("Injury type");
		}
		injury.setProccesed(false);
		injury.setPatient(patient);
		injury.setInjuryType(injuryType);
		
		rulesSession.setGlobal("currentPatient", patient.getUsername());
		// OVO BI TREBALO DA SE UBACI PRI LOGINU I RADI, ALI KREIRA NOVU SESIJU
		//rulesSession.insert(patient);
		// PROBA SA APPLICATION SCOPE
		// application scope radi kada je fetch type za medical history eager
		rulesSession.getAgenda().getAgendaGroup("new-injury").setFocus();
		rulesSession.insert(injury);
		rulesSession.fireAllRules();
		
		// dobavi izmenjenog pacijenta
		rulesSession.getAgenda().getAgendaGroup("MAIN").setFocus();
		QueryResults results = rulesSession.getQueryResults("getPatient", patient.getId());
		for (QueryResultsRow row : results) {
			patient = (Patient) row.get("$patient");
		}
		
		injury = injuryRepository.save(injury);
		patientRepository.save(patient);
		
		return injury;
	}
}
