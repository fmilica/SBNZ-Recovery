package com.sbnz.recovery.dto;

import java.util.ArrayList;
import java.util.List;

public class MealDTO {

	private Long id;
	private String name;
	private List<IngredientAmountDTO> ingredients;
	private String mealDescription;
	
	private double totalCalories;
	
	public MealDTO() {
		this.ingredients = new ArrayList<IngredientAmountDTO>();
	}

	public MealDTO(Long id, String name, List<IngredientAmountDTO> ingredients, String mealDescription, double totalCalories) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.mealDescription = mealDescription;
		this.totalCalories = totalCalories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<IngredientAmountDTO> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientAmountDTO> ingredients) {
		this.ingredients = ingredients;
	}

	public String getMealDescription() {
		return mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	public double getTotalCalories() {
		return totalCalories;
	}

	public void setTotalCalories(double totalCalories) {
		this.totalCalories = totalCalories;
	}
}
