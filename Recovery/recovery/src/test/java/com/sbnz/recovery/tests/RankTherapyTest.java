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

public class RankTherapyTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String chosenPatientUsername = "username";
	
	private KieSession kieSession;
	
	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.setGlobal("chosenPatientUsername", chosenPatientUsername);
		kieSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
	}
	
	@Test
	public void rankTherapy() throws ParseException {
		Therapy therapy1 = new Therapy("T1", TherapyType.ELECTRICITY, 10, 25.0, 3);
		therapy1.addApplicableIllness(Illness.LOW_BLOOD_PRESSURE);
		therapy1.addApplicableIllness(Illness.HIGH_BLOOD_PRESSURE);
		therapy1.addApplicableInjuryType(InjuryType.FRACTURE);
		
		Therapy therapy2 = new Therapy("T2", TherapyType.ELECTRICITY, 20, 25.0, 3);
		therapy2.addApplicableIllness(Illness.DIABETES);
		therapy2.addApplicableIllness(Illness.LOW_BLOOD_PRESSURE);
		therapy2.addApplicableInjuryType(InjuryType.FRACTURE);
		
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		patient.setDailyCaloryIntakeAfterInjury(1836.45);
		// bolesti
		patient.addIllness(Illness.LOW_BLOOD_PRESSURE);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", InjuryType.FRACTURE, InjuryBodyPart.LEG);
		// primenjene terapije
		injury1.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 3), therapy1));
		injury1.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 5), therapy2));
		patient.addInjury(injury1);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 5, 12), null, "desc", InjuryType.MUSCLE_STRAIN, InjuryBodyPart.ARM);
		// primenjene terapije
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 15), therapy1));
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 17), therapy1));
		patient.addInjury(injury2);
		// NEAKTIVNA povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 5, 4), LocalDate.of(2021, 5, 7), "desc", InjuryType.MUSCLE_STRAIN, InjuryBodyPart.ARM);
		// primenjene terapije
		injury3.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 5), therapy1));
		injury3.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 6), therapy1));
		patient.addInjury(injury3);

		kieSession.insert(therapy1);
		kieSession.insert(therapy2);
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		// obe terapije se mogu primeniti
		assertEquals(2, firedRules);
		
		kieSession.getAgenda().getAgendaGroup("rank-therapy").setFocus();
		firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
	}
	
}
