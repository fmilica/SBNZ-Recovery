package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.Injury;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.InjuryBodyPart;
import com.sbnz.recovery.model.enums.PhysicalActivity;
import com.sbnz.recovery.model.templates.GenderIntervalTemplateModel;

public class TemplateGenderIntervalTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private KieSession kieSession;
	private Map<Integer, Double> result;
	private InjuryType fracture;
	
	@Before
	public void setUp() throws IOException, MavenInvocationException {
		// generate template file
		List<GenderIntervalTemplateModel> data = new ArrayList<GenderIntervalTemplateModel>();
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate endDate = LocalDate.of(2022, 1, 1);
		data.add(new GenderIntervalTemplateModel(startDate, endDate, Gender.FEMALE));
		data.add(new GenderIntervalTemplateModel(startDate, endDate, Gender.MALE));
	
		InputStream template = new FileInputStream("../drools-spring-kjar/src/main/resources/templates/interval-count.drt");
		
		ObjectDataCompiler converter = new ObjectDataCompiler();
		String drl = converter.compile(data, template);
		
		FileOutputStream drlFile = new FileOutputStream(new File(
				"../drools-spring-kjar/src/main/resources/rules/interval-count.drl"));
		drlFile.write(drl.getBytes());
		drlFile.close();
		
		// recompile drools-spring-kjar
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
		request.setGoals(Collections.singletonList("install"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
		invoker.execute(request);
		fracture = new InjuryType(1L, "FRACTURE");
		// setup session
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        kieSession = kc.newKieSession("rulesSession");
        result = new HashMap<Integer, Double>();
        kieSession.setGlobal("result", result);
        kieSession.getAgenda().getAgendaGroup("template-rules").setFocus();
	}

	@Test
	public void templateRulesTest() throws ParseException {
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new HashSet<>());
		Injury injury1 = new Injury("I1", LocalDate.of(2021, 5, 1), null, "desc", fracture, InjuryBodyPart.LEG);
		patient.addInjury(injury1);
		kieSession.insert(patient);
		
		kieSession.fireAllRules();
		result = (Map<Integer, Double>) kieSession.getGlobal("result");
		assertEquals(2, result.size());
		double femaleCount = result.get(Gender.FEMALE.ordinal());
		double maleCount = result.get(Gender.MALE.ordinal());
		assertEquals(1, femaleCount, 0.1);
		assertEquals(0, maleCount, 0.1);
	}
}
