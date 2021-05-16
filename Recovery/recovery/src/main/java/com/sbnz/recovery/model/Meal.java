package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Meal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Map<Ingredient, Double> ingredients;
	private String mealDescription;
	
	private double totalCalories;
	
	public Meal(String name, Map<Ingredient, Double> ingredients, String mealDescription) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.mealDescription = mealDescription;
	}

	public Meal(Long id, String name, Map<Ingredient, Double> nutrition, String mealDescription, double totalCalories) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = nutrition;
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

	public Map<Ingredient, Double> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Map<Ingredient, Double> ingredients) {
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

	public void addIngredient(Ingredient ingredient, double quantity) {
		if (this.ingredients == null) {
			this.ingredients = new HashMap<Ingredient, Double>();
		}
		this.ingredients.put(ingredient, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mealDescription == null) {
			if (other.mealDescription != null)
				return false;
		} else if (!mealDescription.equals(other.mealDescription))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", mealDescription=" + mealDescription
				+ "]";
	}
}
