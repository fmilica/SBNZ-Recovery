package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class BmrAndRegularDailyCaloriIntakeTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

	private KieSession kieSession;

	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		//kieSession.getAgenda().getAgendaGroup("bmr").setFocus();
	}

	@Test
	public void CalculateBmrAndDailyCalorieIntakeRuleFemale() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
	
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(1484.0, patient.getBmr(), 0.9);
		assertEquals(2040.5, patient.getRegularDailyCaloryIntake(), 0.9);
	}
	
	@Test
	public void CalculateBmrAndDailyCalorieIntakeRuleMale() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.MALE, dateOfBirth, 172, 68, PhysicalActivity.SEDENTARY, new ArrayList<>());
	
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(1650.0, patient.getBmr(), 0.9);
		assertEquals(1980.0, patient.getRegularDailyCaloryIntake(), 0.9);
	}
}