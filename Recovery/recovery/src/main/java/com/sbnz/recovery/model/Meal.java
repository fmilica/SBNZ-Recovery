package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Meal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "meal")
//	@Transient
//	private Map<Ingredient, Double> ingredients;
	@ManyToMany
	@JoinTable(
	  name = "meal_ingredient", 
	  joinColumns = @JoinColumn(name = "meal_id"), 
	  inverseJoinColumns = @JoinColumn(name = "ingredient_amount_id"))
	private List<IngredientAmount> ingredients;
	
	@Column(name="meal_description")
	private String mealDescription;
	
	@Column(name="total_calories")
	private double totalCalories;
	
//	@ManyToOne
//	@JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = true)
//	private Patient patient;
	
	public Meal() {
		super();
		this.ingredients = new ArrayList<IngredientAmount>();
	}
	
	public Meal(String name, List<IngredientAmount> ingredients, String mealDescription) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.mealDescription = mealDescription;
	}

	public Meal(Long id, String name, List<IngredientAmount> nutrition, String mealDescription, double totalCalories) {
		super();
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

	public List<IngredientAmount> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<IngredientAmount> ingredients) {
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
			this.ingredients = new ArrayList<IngredientAmount>();
		}
		//kreiranje ingredientamount u bazi pa dodavanje u listu 
		this.ingredients.add(new IngredientAmount(ingredient, quantity));
	}

//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}

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
