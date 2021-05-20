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

public class DailyCalorieIntakeAfterInjury {

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
	public void CalculateCalorieIntakeAfterInjuryLAToSedentary() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
	
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(0.9 * patient.getRegularDailyCaloryIntake(), patient.getDailyCaloryIntakeAfterInjury(), 0.9);
	}
	
	@Test
	public void CalculateCalorieIntakeAfterInjuryEAToSedentary() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.EXTRA_ACTIVE, new ArrayList<>());
	
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(0.6 * patient.getRegularDailyCaloryIntake(), patient.getDailyCaloryIntakeAfterInjury(), 0.9);
	}
	
	@Test
	public void CalculateCalorieIntakeAfterInjuryVAToLA() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.VERY_ACTIVE, new ArrayList<>());
	
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.LIGHT_ACTIVE);
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(0.8 * patient.getRegularDailyCaloryIntake(), patient.getDailyCaloryIntakeAfterInjury(), 0.9);
	}
}