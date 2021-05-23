package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.Illness;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.InjuryType;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class NewUpdateInjuryTest {
	
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String currentPatient = "username";

	private KieSession kieSession;

	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.setGlobal("currentPatient", currentPatient);
	}

	@Test
	public void AddInjuryTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		
		FactHandle patientHandle = kieSession.insert(patient);
		
		kieSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		
		// dodavanje povrede
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		patient.addInjury(injury1);
		
		kieSession.update(patientHandle, patient);
		kieSession.getAgenda().getAgendaGroup("new-injury").setFocus();
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
		
		// dodavanje povrede
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		patient.addInjury(injury2);
		
		kieSession.update(patientHandle, patient);
		kieSession.getAgenda().getAgendaGroup("new-injury").setFocus();
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void FinalizeInjuryTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda
		Long injuryId = 1L;
		Injury injury1 = new Injury(injuryId, "I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		patient.addInjury(injury1);
		Injury injury2 = new Injury(2L, "I2", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		patient.addInjury(injury2);
		
		kieSession.insert(patient);
		
		kieSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(6, firedRules);
		
		// finalizacija povrede
		kieSession.setGlobal("injuryId", injuryId);
		kieSession.getAgenda().getAgendaGroup("finalize-injury").setFocus();
		firedRules = kieSession.fireAllRules();
		
		assertEquals(7, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
	}

}
