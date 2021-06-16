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
import org.kie.api.runtime.rule.FactHandle;

import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.PhysicalActivity;
import com.sbnz.recovery.model.events.MealEvent;

public class MealCEPTest {

private KieSession kieSession;

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String currentPatient = "username";

	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        kieSession = kc.newKieSession("cepKsession");
	}
	
	@Test
	public void failedLoginTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
	
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setDailyCaloryIntakeAfterInjury(1632.4);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		
		kieSession.insert(patient);
		kieSession.insert(new MealEvent("username", 500.00));
		int firedRules = kieSession.fireAllRules();
		kieSession.insert(new MealEvent("username", 100.00));
		firedRules = kieSession.fireAllRules();
		kieSession.insert(new MealEvent("username", 450.00));
		firedRules = kieSession.fireAllRules();
		kieSession.insert(new MealEvent("username", 50.00));
		firedRules = kieSession.fireAllRules();
		kieSession.insert(new MealEvent("username", 500.00));
		firedRules = kieSession.fireAllRules();
		kieSession.insert(new MealEvent("username", 20.00));
		firedRules = kieSession.fireAllRules();
		FactHandle handle = kieSession.insert(new MealEvent("username", 90.00));
		firedRules = kieSession.fireAllRules();	
		kieSession.delete(handle);
		kieSession.insert(new MealEvent("username", 1.00));
		firedRules = kieSession.fireAllRules();	
		
		assertEquals(1, firedRules);
	}
}
