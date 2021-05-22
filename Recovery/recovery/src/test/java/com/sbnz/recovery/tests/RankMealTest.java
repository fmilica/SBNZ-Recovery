package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Meal;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.enums.Gender;
import com.sbnz.recovery.model.enums.Illness;
import com.sbnz.recovery.model.enums.PhysicalActivity;

public class RankMealTest {

	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final String chosenPatientUsername = "username";
	private List<Meal> patientMeals;
	
	private KieSession kieSession;
	
	@Before
	public void setUp() {
		patientMeals = new ArrayList<Meal>();
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
		kieSession.setGlobal("chosenPatientUsername", chosenPatientUsername);
		kieSession.setGlobal("mealList", patientMeals);
		kieSession.getAgenda().getAgendaGroup("rank-meal").setFocus();
	}
	
	@Test
	public void rankMealForPatient() throws ParseException {
		// sastojci
		Ingredient ing1 = new Ingredient(100.00, 50.00, 30.00, 5.00);
		ing1.addIllness(Illness.DIABETES);
		Ingredient ing2 = new Ingredient(90.00, 200.00, 30.00, 5.00);
		ing2.addIllness(Illness.DIABETES);
		ing2.addIllness(Illness.HIGH_BLOOD_PRESSURE);
		// obrok 1
		Map<Ingredient, Double> mealIngs1 = new HashMap<>();
		mealIngs1.put(ing1, 100.0);
		mealIngs1.put(ing2, 200.0);
		Meal meal1 = new Meal("M1", mealIngs1, "opis");
		meal1.setTotalCalories(280.0);
		// obrok 2
		Map<Ingredient, Double> mealIngs2 = new HashMap<>();
		mealIngs2.put(ing1, 100.0);
		Meal meal2 = new Meal("M2", mealIngs2, "opis");
		meal2.setTotalCalories(100.0);
		
		// pacijent
		Date dateOfBirth = format.parse("1998/10/10");
		Patient patient = new Patient("username", "password", "name", "surname",
				Gender.FEMALE, dateOfBirth, 172, 68, PhysicalActivity.LIGHT_ACTIVE, new ArrayList<>());
		patient.setBmr(1484.0);
		patient.setRegularDailyCaloryIntake(2040.5);
		patient.setPhysicalActivityAfterInjury(PhysicalActivity.SEDENTARY);
		patient.setDailyCaloryIntakeAfterInjury(1836.45);
		// bolesti
		patient.addIllness(Illness.DIABETES);
		
		kieSession.insert(meal1);
		kieSession.insert(meal2);
		kieSession.insert(patient);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(2, firedRules);
		assertEquals(2, patientMeals.size());
		assertEquals(meal1.getName(), patientMeals.get(0).getName());
		assertEquals(meal2.getName(), patientMeals.get(1).getName());
	}
}
