package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.Illness;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.InjuryType;
import com.sbnz.recovery.model.enums.PhysicalActivity;
import com.sbnz.recovery.model.enums.TherapyType;

public class ReportsTest {
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String chosenPatientUsername = "username";
	private Patient patient;
	private List<Patient> patientReport;
	
	private KieSession kieSession;
	
	@Before
	public void setUp() throws ParseException {
		patientReport = new ArrayList<Patient>();
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.setGlobal("patientReport", patientReport);
		
		// patient
		Date dateOfBirth = format.parse("1998/10/10");
		patient = new Patient(chosenPatientUsername, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		patient.setDailyCaloryIntakeAfterInjury(1836.45);
	}
	
	// ABUSE
	@Test
	public void potentialAbuseTestPositive() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("abuse-report").setFocus();
		
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 1, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		patient.addInjury(injury1);
		patient.addInjury(injury2);
		patient.addInjury(injury3);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, patientReport.size());
		assertEquals(patient.getUsername(), patientReport.get(0).getUsername());
	}
	
	@Test
	public void potentialAbuseTestNegative() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("abuse-report").setFocus();
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2020, 1, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		patient.addInjury(injury1);
		patient.addInjury(injury2);
		patient.addInjury(injury3);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		assertEquals(0, patientReport.size());
	}
	
	//ATROPHY
	@Test
	public void potentialAtrophyTestPositive() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("atrophy-report").setFocus();
		// REST terapija
		Therapy therapy1 = new Therapy("T1", TherapyType.REST, 10, 25.0, 3);
		therapy1.addApplicableIllness(Illness.LOW_BLOOD_PRESSURE);
		therapy1.addApplicableIllness(Illness.HIGH_BLOOD_PRESSURE);
		therapy1.addApplicableInjuryType(InjuryType.FRACTURE);
		
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 3, 20), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 4
		Injury injury4 = new Injury("I4", LocalDate.of(2020, 5, 30), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// lecenja
		injury1.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 3), therapy1));
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 4, 13), therapy1));
		injury3.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 3, 23), therapy1));
		injury4.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2020, 6, 2), therapy1));
		
		patient.addInjury(injury1);
		patient.addInjury(injury2);
		patient.addInjury(injury3);
		patient.addInjury(injury4);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, patientReport.size());
		assertEquals(patient.getUsername(), patientReport.get(0).getUsername());
	}
	
	@Test
	public void potentialAtrophyTestNegative() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("atrophy-report").setFocus();
		// REST terapija
		Therapy therapy1 = new Therapy("T1", TherapyType.REST, 10, 25.0, 3);
		therapy1.addApplicableIllness(Illness.LOW_BLOOD_PRESSURE);
		therapy1.addApplicableIllness(Illness.HIGH_BLOOD_PRESSURE);
		therapy1.addApplicableInjuryType(InjuryType.FRACTURE);
		
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 3, 20), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// povreda 4
		Injury injury4 = new Injury("I4", LocalDate.of(2020, 5, 30), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		// lecenja
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 4, 13), therapy1));
		injury3.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 3, 23), therapy1));
		injury4.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2020, 6, 2), therapy1));
		
		patient.addInjury(injury1);
		patient.addInjury(injury2);
		patient.addInjury(injury3);
		patient.addInjury(injury4);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		assertEquals(0, patientReport.size());
	}
	
	//EATING DISORDER
	@Test
	public void potentialEatingDisorderTestPositive() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("eating-report").setFocus();
		patient.setWeight(16.9);
		patient.setHeight(105.4);
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 20), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.ARM);
		
		patient.addInjury(injury1);
		patient.addInjury(injury2);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, patientReport.size());
		assertEquals(patient.getUsername(), patientReport.get(0).getUsername());
	}
	
	@Test
	public void potentialEatingDisorderTestNegative() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("eating-report").setFocus();
		patient.setWeight(16.9);
		patient.setHeight(105.4);
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 12), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		
		patient.addInjury(injury1);

		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		assertEquals(0, patientReport.size());
	}
}
