package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class IngredientAmount implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column(name="ingredient")
	@ManyToOne
	@JoinColumn(name = "ingredient_id", referencedColumnName = "id", nullable = false)
	private Ingredient ingredient;
	
	@Column(name="amount")
	private double amount;
	
//	@ManyToOne
//	@JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = false)
	@ManyToMany(mappedBy = "ingredients")
	private Set<Meal> meals;

	public IngredientAmount() {
		super();
	}
	
	public IngredientAmount(Ingredient ingredient, double amount) {
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public IngredientAmount(Long id, Ingredient ingredient, double amount) {
		this.id = id;
		this.ingredient = ingredient;
		this.amount = amount;
		this.meals = new HashSet<Meal>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Set<Meal> getMeal() {
		return meals;
	}

	public void setMeal(Set<Meal> meal) {
		this.meals = meal;
	}
	
	public void addMeal(Meal meal) {
		this.meals.add(meal);
	}
}
