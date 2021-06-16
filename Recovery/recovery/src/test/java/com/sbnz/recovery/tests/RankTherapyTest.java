package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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

public class RankTherapyTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String chosenPatientUsername = "username";
	
	private KieSession kieSession;
	
	private InjuryType fracture;
	private InjuryType internal;
	private InjuryType muscle;
	private Illness diabetes;
	private Illness hbp;
	private Illness lbp;
	
	@Before
	public void setUp() {
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
		kieSession.setGlobal("chosenPatientUsername", chosenPatientUsername);
		kieSession.getAgenda().getAgendaGroup("find-therapy").setFocus();
	}
	
	@Test
	public void rankTherapy() throws ParseException {
		
		Therapy therapy1 = new Therapy("T1", TherapyType.ELECTRICITY, 10, 25.0, 3);
		therapy1.addApplicableIllness(lbp);
		therapy1.addApplicableIllness(hbp);
		therapy1.addApplicableInjuryType(fracture);
		
		Therapy therapy2 = new Therapy("T2", TherapyType.ELECTRICITY, 20, 25.0, 3);
		therapy2.addApplicableIllness(diabetes);
		therapy2.addApplicableIllness(lbp);
		therapy2.addApplicableInjuryType(fracture);
		
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		patient.setDailyCaloryIntakeAfterInjury(1836.45);
		// bolesti
		patient.addIllness(lbp);
		// povreda 1
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.LEG);
		// primenjene terapije
		injury1.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 3), therapy1));
		injury1.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 5), therapy2));
		patient.addInjury(injury1);
		// povreda 2
		Injury injury2 = new Injury("I2", LocalDate.of(2021, 5, 12), null, "desc", muscle, InjuryBodyPart.ARM);
		// primenjene terapije
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 15), therapy1));
		injury2.addAppliedTherapy(new AppliedTherapy(LocalDate.of(2021, 5, 17), therapy1));
		patient.addInjury(injury2);
		// NEAKTIVNA povreda 3
		Injury injury3 = new Injury("I3", LocalDate.of(2021, 5, 4), LocalDate.of(2021, 5, 7), "desc", muscle, InjuryBodyPart.ARM);
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
