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

import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class NewActivityLevelTest {

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
		kieSession.getAgenda().getAgendaGroup("new-activity-level").setFocus();
	}

	@Test
	public void DetermineActivityLevelAfterInjuryKidney() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("povreda bubrega", LocalDate.now(), null, "opis", new InjuryType(1L, "INTERNAL"), InjuryBodyPart.KIDNEY);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterInjuryLiver() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("povreda jetre", LocalDate.now(), null, "opis", new InjuryType(1L, "INTERNAL"), InjuryBodyPart.LIVER);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainYoung() throws ParseException {
		Date dateOfBirth = format.parse("2003/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", new InjuryType(1L, "MUSCLE_STRAIN"), InjuryBodyPart.ARM);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainMiddle() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", new InjuryType(1L, "MUSCLE_STRAIN"), InjuryBodyPart.ARM);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.LIGHT_ACTIVE, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainOld() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", new InjuryType(1L, "MUSCLE_STRAIN"), InjuryBodyPart.ARM);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterLowerBodyFracture() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("prelom noge", LocalDate.now(), null, "opis", new InjuryType(1L, "FRACTURE"), InjuryBodyPart.LEG);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureYoung() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", new InjuryType(1L, "FRACTURE"), InjuryBodyPart.ARM);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureOld() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", new InjuryType(1L, "FRACTURE"), InjuryBodyPart.ARM);
		injuries.add(injury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.LIGHT_ACTIVE, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureAndLiverInjury() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", new InjuryType(1L, "FRACTURE"), InjuryBodyPart.ARM);
		injuries.add(injury);
		Injury liverInjury = new Injury("povreda jetre", LocalDate.now(), null, "opis", new InjuryType(2L, "INTERNAL"), InjuryBodyPart.LIVER);
		injuries.add(liverInjury);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<Illness>());
		
		patient.setMedicalHistory(injuries);
		
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
	}
	
	@Test
	public void DetermineActivityLevelAfterInjuryShouldNotFire() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.MALE, dateOfBirth, 172, 68, PhysicalActivity.SEDENTARY, new ArrayList<>());
	
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(null, patient.getPhysicalActivityAfterInjury());
	}
}