package com.sbnz.recovery.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KnowledgeSessionService {

	private static Logger log = LoggerFactory.getLogger(KnowledgeSessionService.class);
	
	private final KieContainer kieContainer;
    private KieSession rulesSession;
    private KieSession cepSession;
    
    
    @Autowired
    public KnowledgeSessionService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public KieSession getRulesSession() {
    	if (this.rulesSession == null) {
    		log.info("Initialized session");
        	rulesSession = kieContainer.newKieSession("rulesSession");
    	}
        return rulesSession;
    }

    public void setRulesSession(KieSession kieSession) {
        this.rulesSession = kieSession;
    }
    
    public KieSession getCepSession() {
    	if (this.rulesSession == null) {
    		log.info("Initialized session");
        	rulesSession = kieContainer.newKieSession("cepKsession");
    	}
        return rulesSession;
    }

    public void setCepSession(KieSession kieSession) {
        this.cepSession = kieSession;
    }
    
    public void releaseRulesSession(){
        this.cepSession.dispose();
        this.cepSession = null;
    }
    
}
