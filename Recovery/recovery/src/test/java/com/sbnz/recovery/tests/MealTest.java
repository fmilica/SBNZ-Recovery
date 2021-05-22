package com.sbnz.recovery.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.sbnz.recovery.model.Ingredient;
import com.sbnz.recovery.model.Meal;

public class MealTest {

	private KieSession kieSession;
	
	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("rulesSession");
	}
	
	@Test
	public void mealClassificationDiabetesTest() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("classify-ingredient").setFocus();
		
		Ingredient ing = new Ingredient(100.00, 50.00, 30.00, 5.00);
		
		kieSession.insert(ing);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, ing.getIllnesses().size());
	}
	
	@Test
	public void mealClassificationHBPTest() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("classify-ingredient").setFocus();
		
		Ingredient ing = new Ingredient(90.00, 200.00, 30.00, 5.00);
		
		kieSession.insert(ing);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, ing.getIllnesses().size());
	}
	
	@Test
	public void mealClassificationLBPTest() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("classify-ingredient").setFocus();
		
		Ingredient ing = new Ingredient(20.00, 200.00, 70.00, 5.00);
		
		kieSession.insert(ing);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(1, ing.getIllnesses().size());
	}
	
	@Test
	public void calculateCalories() throws ParseException {
		kieSession.getAgenda().getAgendaGroup("meal").setFocus();
		
		Ingredient ing1 = new Ingredient(100.00, 50.00, 30.00, 5.00);
		Ingredient ing2 = new Ingredient(90.00, 200.00, 30.00, 5.00);
		Ingredient ing3 = new Ingredient(20.00, 200.00, 70.00, 5.00);
		HashMap<Ingredient, Double> ingredients = new HashMap<Ingredient, Double>();
		ingredients.put(ing1, 1.00);
		ingredients.put(ing2, 1.00);
		ingredients.put(ing3, 1.00);
		
		Meal meal = new Meal("Apple soup", ingredients, "Low cooking");
		
		kieSession.insert(meal);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
		assertEquals(210.0, meal.getTotalCalories(), 0.9);
	}
}