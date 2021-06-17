package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Meal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "meal_ingredient", 
	  joinColumns = @JoinColumn(name = "meal_id"), 
	  inverseJoinColumns = @JoinColumn(name = "ingredient_amount_id"))
	//@Fetch(FetchMode.JOIN)
	private Set<IngredientAmount> ingredients;
	
	@Column(name="meal_description")
	private String mealDescription;
	
	@Column(name="total_calories")
	private double totalCalories;
	
//	@ManyToOne
//	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
//	private Patient patient;
	
	@ManyToMany(mappedBy = "meals")
	private Set<DailyMeal> dailyMeal;
	
	public Meal() {
		this.ingredients = new HashSet<IngredientAmount>();
	}
	
	public Meal(String name, Set<IngredientAmount> ingredients, String mealDescription) {
		this.name = name;
		this.ingredients = ingredients;
		this.mealDescription = mealDescription;
	}

	public Meal(Long id, String name, Set<IngredientAmount> nutrition, String mealDescription, double totalCalories) {
		this.id = id;
		this.name = name;
		this.ingredients = nutrition;
		this.mealDescription = mealDescription;
		this.totalCalories = totalCalories;
//		this.patient = patient;
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

	public Set<IngredientAmount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<IngredientAmount> ingredients) {
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
			this.ingredients = new HashSet<IngredientAmount>();
		}
		//kreiranje ingredientamount u bazi pa dodavanje u listu 
		this.ingredients.add(new IngredientAmount(ingredient, quantity));
	}
	

	public Set<DailyMeal> getDailyMeal() {
		return dailyMeal;
	}

	public void setDailyMeal(Set<DailyMeal> dailyMeal) {
		this.dailyMeal = dailyMeal;
	}

	@Override
	public String toString() {
		return "Meal [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", mealDescription=" + mealDescription
				+ "]";
	}
}
