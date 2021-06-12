package com.sbnz.recovery.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class KieConfiguration {
	
	@Autowired
	private KieSessionHolder kieSessionHolder;
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean(name = "cepSession")
	public KieSession eventsSession() {
		KieSession kieSession = this.kieContainer().newKieSession("cepKsession");
		System.out.println("Creating new events kie session");
		return kieSession;
	}
	
	@Bean(name = "rulesSession")
	//@SessionScope
	public KieSession kieSession() {
		KieSession kieSession = this.kieContainer().newKieSession("rulesSession");
		System.out.println("Creating new kie session");
		//this.kieSessionHolder.add(kieSession);
		return kieSession;
	}
}
