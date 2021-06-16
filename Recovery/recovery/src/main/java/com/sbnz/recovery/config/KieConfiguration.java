package com.sbnz.recovery.config;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.InjuryRequirement;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.repository.IllnessRepository;
import com.sbnz.recovery.repository.InjuryRequirementRepository;
import com.sbnz.recovery.repository.InjuryTypeRepository;
import com.sbnz.recovery.repository.PatientRepository;
import com.sbnz.recovery.repository.TherapyRepository;

@Configuration
public class KieConfiguration {
	
	@Autowired
	private KieSessionHolder kieSessionHolder;
	
	@Autowired
	private InjuryRequirementRepository injuryRequirementRepository;
	
	@Autowired
	private IllnessRepository illnessRepository;
	
	@Autowired
	private InjuryTypeRepository injuryTypeRepository;
	
	@Autowired
	private TherapyRepository therapyRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean(name = "cepSession")
	public KieSession eventsSession() {
		KieSession kieSession = this.kieContainer().newKieSession("cepKsession");
		System.out.println("Creating new events kie session");
		return kieSession;
	}
	
	@Bean(name = "rulesSession")
	//@SessionScope
	public KieSession kieSession() {
		KieSession kieSession = this.kieContainer().newKieSession("rulesSession");
		System.out.println("Creating new kie session");
		// ubacivanje svih injuryRequirement objekata
		List<InjuryRequirement> injuryRequirements = injuryRequirementRepository.findAll();
		for (InjuryRequirement injuryRequirement : injuryRequirements) {
			kieSession.insert(injuryRequirement);
		}
		// ubacivanje svih illness objekata
		List<Illness> illnesses = illnessRepository.findAll();
		for (Illness illness : illnesses) {
			kieSession.insert(illness);
		}
		// ubacivanje svih injuryType objekata
		List<InjuryType> injuryTypes = injuryTypeRepository.findAll();
		for (InjuryType injuryType : injuryTypes) {
			kieSession.insert(injuryType);
		}
		// ubacivanje svih therapy objekata
		List<Therapy> therapies = therapyRepository.findAll();
		for (Therapy therapy : therapies) {
			kieSession.insert(therapy);
		}
		// ubacivanje svih patient objekata
		List<Patient> patients = patientRepository.findAll();
		for (Patient patient : patients) {
			kieSession.insert(patient);
		}
		//this.kieSessionHolder.add(kieSession);
		return kieSession;
	}
}
