package com.sbnz.recovery.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.model.Patient;

@Service
public class TestService {

	@Autowired
	private KnowledgeSessionService knowledgeSessionService;
	
	public Patient calculateBmi(Patient patient) {
		KieSession kieSession = knowledgeSessionService.getRulesSession();
		kieSession.insert(patient);
		kieSession.fireAllRules();
		return patient;
	}
}
