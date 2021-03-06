package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.ChosenPatient;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.InjuryRequirement;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class NewUpdateInjuryTest {
	
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String currentPatient = "username";
	
	private InjuryType fracture;
	private InjuryType internal;
	private InjuryType muscle;
	private Illness diabetes;
	private Illness hbp;
	private Illness lbp;
	private InjuryRequirement injuryReqUpperBody;
	private InjuryRequirement injuryReqLowerBody;

	private KieSession kieSession;

	@Before
	public void setUp() {
		fracture = new InjuryType(1L, "FRACTURE");
		muscle = new InjuryType(2L, "MUSCLE_STRAIN");
		internal = new InjuryType(3L, "INTERNAL");
		diabetes = new Illness(1L, "DIABETES");
		hbp = new Illness(2L, "HIGH_BLOOD_PRESSURE");
		lbp = new Illness(3L, "LOW_BLOOD_PRESSURE");
		injuryReqUpperBody = new InjuryRequirement(1L, 0, 30, fracture, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.WRIST)), 
						PhysicalActivity.MODERATE);
		injuryReqLowerBody = new InjuryRequirement(2L, 0, 130, fracture, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.LEG, InjuryBodyPart.KNEE)), 
						PhysicalActivity.SEDENTARY);
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.insert(injuryReqUpperBody);
		kieSession.insert(injuryReqLowerBody);
	}

	@Test
	public void AddInjuryTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
		patient.setId(1L);
		// bolesti
		patient.addIllness(lbp);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		kieSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(5, firedRules);
		assertEquals(true, chosen.isResolved());
		
		// dodavanje povrede
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.ARM);
		injury1.setId(1L);
		injury1.setPatient(patient);
		
		kieSession.insert(injury1);

		chosen = new ChosenPatient(1L, 1L);
		kieSession.insert(chosen);
		
		kieSession.getAgenda().getAgendaGroup("new-injury").setFocus();
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
		
		// dodavanje povrede
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.LEG);
		injury2.setId(2L);
		injury2.setPatient(patient);
		
		kieSession.insert(injury2);

		chosen = new ChosenPatient(1L, 2L);
		kieSession.insert(chosen);
		
		kieSession.getAgenda().getAgendaGroup("new-injury").setFocus();
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void FinalizeInjuryTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
		patient.setId(1L);
		// bolesti
		patient.addIllness(lbp);
		// povreda
		Long injuryId = 1L;
		Injury injury1 = new Injury(injuryId, "I1", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.LEG);
		patient.addInjury(injury1);
		Injury injury2 = new Injury(2L, "I2", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.ARM);
		patient.addInjury(injury2);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		kieSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(6, firedRules);
		
		// finalizacija povrede
		kieSession.getAgenda().getAgendaGroup("finalize-injury").setFocus();
		
		chosen = new ChosenPatient(1L, 1L);
		kieSession.insert(chosen);
		
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
	}

}
