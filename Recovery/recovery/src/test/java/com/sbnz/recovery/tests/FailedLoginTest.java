package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.events.LoginEvent;

public class FailedLoginTest {

	private KieSession kieSession;
	
	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        kieSession = kc.newKieSession("cepKsession");
	}
	
	@Test
	public void failedLoginTest() throws ParseException {
		
		kieSession.insert(new LoginEvent("patient1"));
		
		int firedRules = kieSession.fireAllRules();
		
		kieSession.insert(new LoginEvent("patient1"));
		
		firedRules = kieSession.fireAllRules();
		
		kieSession.insert(new LoginEvent("patient1"));
		
		firedRules = kieSession.fireAllRules();
		
		kieSession.insert(new LoginEvent("patient1"));
		
		firedRules = kieSession.fireAllRules();
		
		kieSession.insert(new LoginEvent("patient1"));
		
		firedRules = kieSession.fireAllRules();
		
		kieSession.insert(new LoginEvent("patient1"));
		
		firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
	}
}
