package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.Therapy;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.enums.TherapyType;

public class ClassifyTherapyTest {

	private KieSession kieSession;
	private InjuryType fracture;
	private InjuryType internal;
	private InjuryType muscle;
	private Illness diabetes;
	private Illness hbp;
	private Illness lbp;

	@Before
	public void setUp() {
		fracture = new InjuryType(1L, "Fracture");
		muscle = new InjuryType(2L, "Muscle strain");
		internal = new InjuryType(3L, "Internal");
		diabetes = new Illness(1L, "Diabetes");
		hbp = new Illness(2L, "High blood pressure");
		lbp = new Illness(3L, "Low blood pressure");
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.insert(fracture);
		kieSession.insert(muscle);
		kieSession.insert(internal);
		kieSession.insert(diabetes);
		kieSession.insert(hbp);
		kieSession.insert(lbp);
		kieSession.getAgenda().getAgendaGroup("classify-therapy").setFocus();
	}

	@Test
	public void ClassifyTherapyShouldAddDiabetesLowBPFracture() throws ParseException {
		Therapy therapy = new Therapy("terapija", TherapyType.ELECTRICITY, 20, 25.0, 3);
		
		kieSession.insert(therapy);
		
		
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertTrue(therapy.getApplicableIllness().contains(diabetes));
		assertTrue(therapy.getApplicableIllness().contains(lbp));
		assertTrue(therapy.getApplicableInjury().contains(fracture));
	}
	
}