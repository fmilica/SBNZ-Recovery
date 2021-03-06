package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.ChosenPatient;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class BmrAndRegularDailyCaloriIntakeTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String currentPatient = "username";

	private KieSession kieSession;

	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
	}

	@Test
	public void CalculateBmrAndDailyCalorieIntakeRuleFemale() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
		patient.setId(1L);
		
		kieSession.insert(patient);
		
		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(5, firedRules);
		assertEquals(1484.0, patient.getBmr(), 0.9);
		assertEquals(2040.5, patient.getRegularDailyCaloryIntake(), 0.9);
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void CalculateBmrAndDailyCalorieIntakeRuleMale() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.MALE, dateOfBirth, 172, 68, PhysicalActivity.SEDENTARY, new HashSet<>());
		patient.setId(1L);

		kieSession.insert(patient);
		
		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(5, firedRules);
		assertEquals(1650.0, patient.getBmr(), 0.9);
		assertEquals(1980.0, patient.getRegularDailyCaloryIntake(), 0.9);
		assertEquals(true, chosen.isResolved());
	}
}