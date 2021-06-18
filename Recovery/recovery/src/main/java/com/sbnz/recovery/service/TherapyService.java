package com.sbnz.recovery.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.exceptions.NonExistingIdException;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.ChosenPatient;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.enums.AssignType;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
import com.sbnz.recovery.repository.IllnessRepository;
import com.sbnz.recovery.repository.InjuryTypeRepository;
import com.sbnz.recovery.repository.PatientRepository;
import com.sbnz.recovery.repository.TherapyRepository;

@Service
public class TherapyService {

	@Autowired
	private TherapyRepository therapyRepository;
	
	@Autowired
	private AppliedTherapyRepository appliedTherapyRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private IllnessRepository illnessRepository;
	
	@Autowired
	private InjuryTypeRepository injuryTypeRepository;
	
	@Autowired
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	public List<Therapy> getAllTherapies() {
		QueryResults results = rulesSession.getQueryResults("getAllTherapies");
		List<Therapy> therapies = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Therapy therapy = (Therapy) row.get("$therapy");
			therapies.add(therapy);
		}
		return therapies;
		//return therapyRepository.findAll();
	}
	
	public List<Therapy> filterTherapiesByIllnessAndInjuryType(
			Long illnessId, Long injuryTypeId) throws NonExistingIdException {
		QueryResults allResult = rulesSession.getQueryResults("getAllTherapies");
		List<Therapy> therapies = new ArrayList<>();
		
		for (QueryResultsRow row : allResult) {
			Therapy therapy = (Therapy) row.get("$therapy");
			therapies.add(therapy);
		}
		
		if (illnessId != -1) {
			Illness illness = illnessRepository.findById(illnessId).orElse(null);
			if (illness == null) {
				throw new NonExistingIdException("Illness");
			}
			
			QueryResults resultsIllness = rulesSession.getQueryResults("getAllTherapiesByIllness", illness);
			List<Therapy> therapiesIllness = new ArrayList<>();
			
			for (QueryResultsRow row : resultsIllness) {
				Therapy therapy = (Therapy) row.get("$therapy");
				therapiesIllness.add(therapy);
			}
			// presek dve liste
			therapies.retainAll(therapiesIllness);
			
		}
		if (injuryTypeId != -1) {
			InjuryType injuryType = injuryTypeRepository.findById(injuryTypeId).orElse(null);
			if (injuryType == null) {
				throw new NonExistingIdException("Injury type");
			}
			
			QueryResults resultsInjuryType = rulesSession.getQueryResults("getAllTherapiesByInjuryType", injuryType);
			List<Therapy> therapiesInjuryType = new ArrayList<>();
			
			for (QueryResultsRow row : resultsInjuryType) {
				Therapy therapy = (Therapy) row.get("$therapy");
				therapiesInjuryType.add(therapy);
			}
			// presek dve liste
			therapies.retainAll(therapiesInjuryType);
			
		}
		
		return therapies;
	}
	
	public List<AppliedTherapy> findAppliedForCurrent(Long patientId) {
		return appliedTherapyRepository.findAllByApplicationDateAndInjuryPatientId(LocalDate.now(), patientId);
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
	
	public boolean assignTherapy(Long patientId) throws NonExistingIdException {
		Patient patient = patientRepository.findById(patientId).orElse(null);
		if (patient == null) {
			throw new NonExistingIdException("Patient");
		}
		ChosenPatient chosenPatient = new ChosenPatient(patient.getId(), AssignType.THERAPY);
		rulesSession.insert(chosenPatient);
		rulesSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
		rulesSession.fireAllRules();
		rulesSession.getAgenda().getAgendaGroup("rank-therapy").setFocus();
		rulesSession.fireAllRules();
		if (!chosenPatient.isResolved()) {
			// nije dodeljena terapija
			return false;
		}
		// dobavi izmenjenog pacijenta
		rulesSession.getAgenda().getAgendaGroup("MAIN").setFocus();
		QueryResults results = rulesSession.getQueryResults("getPatient", patient.getId());
		for (QueryResultsRow row : results) {
			patient = (Patient) row.get("$patient");
		}
		for (Injury in : patient.getMedicalHistory()) {
			for (AppliedTherapy at : in.getAppliedTherapies()) {
				appliedTherapyRepository.save(at);
			}
		}
		patientRepository.save(patient);
		return true;
	}
}
