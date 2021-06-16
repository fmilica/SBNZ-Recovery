package com.sbnz.recovery.service;

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
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.enums.AssignType;
import com.sbnz.recovery.repository.AppliedTherapyRepository;
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
		rulesSession.insert(new ChosenPatient(patient.getId(), AssignType.THERAPY));
		rulesSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
		rulesSession.fireAllRules();
		rulesSession.getAgenda().getAgendaGroup("rank-therapy").setFocus();
		rulesSession.fireAllRules();
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
	}
}
