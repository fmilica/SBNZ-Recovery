package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.PhysicalActivity;
import com.sbnz.recovery.model.enums.TherapyType;

public class QueriesTest {
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String chosenPatientUsername = "username";
	private Patient patient;
	
	private KieSession kieSession;
	
	private InjuryType fracture;
	private InjuryType internal;
	private InjuryType muscle;
	private Illness diabetes;
	private Illness hbp;
	private Illness lbp;
	
	private Map<InjuryType, Double> injuryTypeCountMap;
	
	@Before
	public void setUp() throws ParseException {
		fracture = new InjuryType(1L, "FRACTURE");
		muscle = new InjuryType(2L, "MUSCLE_STRAIN");
		internal = new InjuryType(3L, "INTERNAL");
		diabetes = new Illness(1L, "DIABETES");
		hbp = new Illness(2L, "HIGH_BLOOD_PRESSURE");
		lbp = new Illness(3L, "LOW_BLOOD_PRESSURE");
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		
		injuryTypeCountMap = new HashMap<>();
		kieSession.setGlobal("injuryTypeCountMap", injuryTypeCountMap);
		kieSession.insert(fracture);
		kieSession.insert(muscle);
		kieSession.insert(internal);
		// patient
		Date dateOfBirth = format.parse("1998/10/10");
		patient = new Patient(chosenPatientUsername, "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		patient.setId(1L);
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		patient.setDailyCaloryIntakeAfterInjury(1836.45);
		// REST terapija
		Therapy therapy1 = new Therapy("T1", TherapyType.REST, 10, 25.0, 3);
		therapy1.addApplicableIllness(lbp);
		therapy1.addApplicableIllness(hbp);
		therapy1.addApplicableInjuryType(fracture);
		
		kieSession.insert(therapy1);
		
		// bolesti
		patient.addIllness(lbp);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.LEG);
		injury1.setId(1L);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 4, 12), null, "desc", fracture, InjuryBodyPart.ARM);
		injury2.setId(2L);
		// povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 3, 20), null, "desc", fracture, InjuryBodyPart.ARM);
		injury3.setId(3L);
		// povreda 4
		Injury injury4 = new Injury("I4", LocalDate.of(2020, 7, 30), null, "desc", fracture, InjuryBodyPart.ARM);
		injury4.setId(4L);
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
	}
	
	@Test
	public void getAllPatientsTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllPatients");
		List<Patient> patients = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Patient patient = (Patient) row.get("$patient");
			patients.add(patient);
		}
		assertEquals(1, patients.size());
		assertEquals(patient.getUsername(), patients.get(0).getUsername());
	}
	
	@Test
	public void getPatientTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getPatient", 1L);
		Patient patient1 = null;
		for (QueryResultsRow row : results) {
			patient1 = (Patient) row.get("$patient");
		}
	}
	
	@Test
	public void getAllPatientInjuriesTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllPatientInjuries", 1L);
		List<Injury> patientInjuries = null;
		
		for (QueryResultsRow row : results) {
			patientInjuries = (List<Injury>) row.get("$patientInjuries");
		}
		assertEquals(4, patientInjuries.size());
	}
	
	@Test
	public void getAllPatientTherapiesTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllPatientTherapies", 1L);
		List<AppliedTherapy> patientTherapies = null;
		
		for (QueryResultsRow row : results) {
			patientTherapies = (List<AppliedTherapy>) row.get("$patientTherapies");
		}
		assertEquals(4, patientTherapies.size());
	}
	
	@Test
	public void getAllPatientTherapiesByInjuryTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllPatientTherapiesByInjury", 1L, 2L);
		List<AppliedTherapy> injuryTherapies = null;
		
		for (QueryResultsRow row : results) {
			injuryTherapies = (List<AppliedTherapy>) row.get("$injuryTherapies");
		}
		assertEquals(1, injuryTherapies.size());
		assertEquals(LocalDate.of(2021, 4, 13), injuryTherapies.get(0).getApplicationDate());
	}
	
	@Test
	public void getAllTherapiesTest() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllTherapies");
		List<Therapy> therapies = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Therapy therapy = (Therapy) row.get("$therapy");
			therapies.add(therapy);
		}
		assertEquals(1, therapies.size());
		assertEquals("T1", therapies.get(0).getName());
	}
	
	@Test
	public void getAllTherapiesByIllnessTestPositive() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllTherapiesByIllness", lbp);
		List<Therapy> therapies = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Therapy therapy = (Therapy) row.get("$therapy");
			therapies.add(therapy);
		}
		assertEquals(1, therapies.size());
		assertEquals("T1", therapies.get(0).getName());
	}
	
	@Test
	public void getAllTherapiesByIllnessTestNegative() throws ParseException {
		QueryResults results = kieSession.getQueryResults("getAllTherapiesByIllness", diabetes);
		List<Therapy> therapies = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Therapy therapy = (Therapy) row.get("$therapy");
			therapies.add(therapy);
		}
		assertEquals(0, therapies.size());
	}
	
	
	@Test
	public void getInjuriesCountByAgeGroupTest() throws ParseException {
		int startAge = 15;
		int endAge = 25;
		LocalDate startDate = LocalDate.parse("2021-01-01");
		LocalDate endDate = LocalDate.parse("2022-01-01");
		QueryResults results = 
				kieSession.getQueryResults("getInjuriesCountByAgeGroup", 
						startAge, endAge, startDate, endDate);
		int total = 0;
		for (QueryResultsRow row : results) {
			total = ((Number)row.get("$totalInjuriesInAgeGroup")).intValue();
		}
		assertEquals(3, total);
	}
	
	@Test
	public void getTotalInjuriesCountByInjuryTypeTest() throws ParseException {
		LocalDate startDate = LocalDate.parse("2021-01-01");
		LocalDate endDate = LocalDate.parse("2022-01-01");
		QueryResults results = 
				kieSession.getQueryResults("getTotalInjuriesCountByInjuryType", 
						fracture, startDate, endDate);
		int total = 0;
		for (QueryResultsRow row : results) {
			total = ((Number)row.get("$totalInjuriesForInjuryType")).intValue();
		}
		assertEquals(3, total);
	}
	
	/*
	@Test
	public void proba() throws ParseException {
		int fired = kieSession.fireAllRules();
		injuryTypeCountMap = (Map<InjuryType, Double>) kieSession.getGlobal("injuryTypeCountMap");
		
		assertEquals(3, injuryTypeCountMap.size());
		assertEquals(3, injuryTypeCountMap.get(fracture), 0.1);
	}*/
}
