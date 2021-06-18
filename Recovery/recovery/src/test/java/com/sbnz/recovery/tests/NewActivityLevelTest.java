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

public class NewActivityLevelTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String currentPatient = "username";

	private KieSession kieSession;

	private InjuryType fracture;
	private InjuryType internal;
	private InjuryType muscle;
	private InjuryRequirement muscleStrainOld;
	private InjuryRequirement muscleStrainMiddle;
	private InjuryRequirement muscleStrainYoung;
	private InjuryRequirement upperBodyFractureOld;
	private InjuryRequirement upperBodyFractureYoung;
	private InjuryRequirement lowerBodyFracture;
	private InjuryRequirement reqInternal;

	@Before
	public void setUp() {
		fracture = new InjuryType(1L, "FRACTURE");
		muscle = new InjuryType(2L, "MUSCLE_STRAIN");
		internal = new InjuryType(3L, "INTERNAL");
		muscleStrainOld = new InjuryRequirement(1L, 50, 130, muscle, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.LEG)), 
				PhysicalActivity.SEDENTARY);
		muscleStrainMiddle = new InjuryRequirement(2L, 20, 50, muscle, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.LEG)), 
				PhysicalActivity.LIGHT_ACTIVE);
		muscleStrainYoung = new InjuryRequirement(3L, 0, 20, muscle, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.LEG)), 
				PhysicalActivity.MODERATE);
		upperBodyFractureOld = new InjuryRequirement(4L, 30, 130, fracture, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.WRIST)), 
						PhysicalActivity.LIGHT_ACTIVE);
		upperBodyFractureYoung = new InjuryRequirement(5L, 0, 30, fracture, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.ARM, InjuryBodyPart.WRIST)), 
				PhysicalActivity.MODERATE);
		lowerBodyFracture = new InjuryRequirement(6L, 0, 130, fracture, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.LEG, InjuryBodyPart.KNEE)), 
						PhysicalActivity.SEDENTARY);
		reqInternal = new InjuryRequirement(7L, 0, 130, internal, 
				new HashSet<InjuryBodyPart>(Arrays.asList(InjuryBodyPart.KIDNEY, InjuryBodyPart.LIVER)), 
				PhysicalActivity.SEDENTARY);
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("new-activity-level").setFocus();
		kieSession.insert(muscleStrainOld);
		kieSession.insert(muscleStrainMiddle);
		kieSession.insert(muscleStrainYoung);
		kieSession.insert(upperBodyFractureYoung);
		kieSession.insert(upperBodyFractureOld);
		kieSession.insert(lowerBodyFracture);
		kieSession.insert(reqInternal);
	}

	@Test
	public void DetermineActivityLevelAfterInjuryKidney() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Injury injury = new Injury("povreda bubrega", LocalDate.now(), null, "opis", internal, InjuryBodyPart.KIDNEY);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterInjuryLiver() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Injury injury = new Injury("povreda jetre", LocalDate.now(), null, "opis", internal, InjuryBodyPart.LIVER);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		
		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);

		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainYoung() throws ParseException {
		Date dateOfBirth = format.parse("2003/10/10");
		
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", muscle, InjuryBodyPart.ARM);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);
		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainMiddle() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", muscle, InjuryBodyPart.ARM);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.LIGHT_ACTIVE, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterMuscleStrainOld() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		Injury injury = new Injury("istegnuce noge", LocalDate.now(), null, "opis", muscle, InjuryBodyPart.ARM);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterLowerBodyFracture() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Injury injury = new Injury("prelom noge", LocalDate.now(), null, "opis", fracture, InjuryBodyPart.LEG);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureYoung() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", fracture, InjuryBodyPart.ARM);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.MODERATE, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureOld() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", fracture, InjuryBodyPart.ARM);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.LIGHT_ACTIVE, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterUpperBodyFractureAndLiverInjury() throws ParseException {
		Date dateOfBirth = format.parse("1968/10/10");
		
		Injury injury = new Injury("prelom ruke", LocalDate.now(), null, "opis", fracture, InjuryBodyPart.ARM);
		Injury liverInjury = new Injury("povreda jetre", LocalDate.now(), null, "opis", internal, InjuryBodyPart.LIVER);
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<Illness>());
		patient.setId(1L);
		patient.addInjury(injury);
		patient.addInjury(liverInjury);
		
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertEquals(PhysicalActivity.SEDENTARY, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
	
	@Test
	public void DetermineActivityLevelAfterInjuryShouldNotFire() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		
		Patient patient = new Patient(currentPatient, "password", "name", "surname",
				Gender.MALE, dateOfBirth, 172, 68, PhysicalActivity.SEDENTARY, new HashSet<>());
		patient.setId(1L);
		kieSession.insert(patient);

		ChosenPatient chosen = new ChosenPatient(1L);
		kieSession.insert(chosen);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(null, patient.getPhysicalActivityAfterInjury());
		assertEquals(true, chosen.isResolved());
	}
}