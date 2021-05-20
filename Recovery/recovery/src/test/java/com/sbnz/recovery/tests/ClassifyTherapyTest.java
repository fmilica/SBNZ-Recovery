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
import com.sbnz.recovery.model.enums.Illness;
import com.sbnz.recovery.model.enums.InjuryType;
import com.sbnz.recovery.model.enums.TherapyType;

public class ClassifyTherapyTest {

	private KieSession kieSession;

	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.getAgenda().getAgendaGroup("classify-therapy").setFocus();
	}

	@Test
	public void ClassifyTherapyShouldAddDiabetesLowBPFracture() throws ParseException {
		Therapy therapy = new Therapy("terapija", TherapyType.ELECTRICITY, 20, 25.0, 3);
		
		kieSession.insert(therapy);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(3, firedRules);
		assertTrue(therapy.getApplicableIllness().contains(Illness.DIABETES));
		assertTrue(therapy.getApplicableIllness().contains(Illness.LOW_BLOOD_PRESSURE));
		assertTrue(therapy.getApplicableInjury().contains(InjuryType.FRACTURE));
	}
	
}