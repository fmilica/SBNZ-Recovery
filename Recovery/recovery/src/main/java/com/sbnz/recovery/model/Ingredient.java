package com.sbnz.recovery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Ingredient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="calories")
	private double calories;
	
	@Column(name="water_percentage")
	private double waterPercentage;
	
	@Column(name="proteins")
	private double proteins;
	
	@Column(name="carbohydrates")
	private double carbohydrates;
	
	@Column(name="sugars")
	private double sugars;
	
	@Column(name="fibers")
	private double fibers;
	
	@Column(name="fat")
	private double fat;
	
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "ingredient")
//	@Transient
	@ManyToMany
	@JoinTable(
	  name = "illness_ingredient", 
	  joinColumns = @JoinColumn(name = "ingredient_id"), 
	  inverseJoinColumns = @JoinColumn(name = "illness_id"))
	private List<Illness> illnesses;
	
//	@Column(name="illnesses_string")
//	private String illnessesString;
	
//	@ManyToOne
//	@JoinColumn(name = "meal_id", referencedColumnName = "id", nullable = true)
//	private Meal meal;
	
//	@OneToMany(mappedBy = "ingredientAmounts")
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "ingredient")
	private List<IngredientAmount> ingredientAmounts;
	
	public Ingredient() {
		super();
		this.illnesses = new ArrayList<Illness>();
	}

	public Ingredient(String name, double calories, double waterPercentage, double proteins, double carbohydrates, double sugars,
			double fibers, double fat) {
		super();
		this.name = name;
		this.calories = calories;
		this.waterPercentage = waterPercentage;
		this.proteins = proteins;
		this.carbohydrates = carbohydrates;
		this.sugars = sugars;
		this.fibers = fibers;
		this.fat = fat;
		this.illnesses = new ArrayList<Illness>();
		
		/*String[] parts = illnesses.split("|");
		for(int i=0;i<parts.length;i++) {
			if(parts[i] == "DIABETES") {
				this.illnesses.add(Illness.DIABETES);
			}else if(parts[i] == "HIGH_BLOOD_PRESSURE") {
				this.illnesses.add(Illness.HIGH_BLOOD_PRESSURE);
			}else {
				this.illnesses.add(Illness.LOW_BLOOD_PRESSURE);
			}
		}*/
	}
	
	public Ingredient(double calories, double carbohydrates, double sugars, double fat) {
		super();
		this.calories = calories;
		this.carbohydrates = carbohydrates;
		this.sugars = sugars;
		this.fat = fat;
		this.illnesses = new ArrayList<Illness>();
	}
	
	public Ingredient(Long id, String name, double calories, double waterPercentage, double proteins, double carbohydrates,
			double sugars, double fibers, double fat) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.waterPercentage = waterPercentage;
		this.proteins = proteins;
		this.carbohydrates = carbohydrates;
		this.sugars = sugars;
		this.fibers = fibers;
		this.fat = fat;
		this.illnesses = new ArrayList<Illness>();
	}

	public Ingredient(Long id, String name, double calories, double waterPercentage, double proteins, double carbohydrates,
			double sugars, double fibers, double fat, List<Illness> illnesses) {
		super();
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.waterPercentage = waterPercentage;
		this.proteins = proteins;
		this.carbohydrates = carbohydrates;
		this.sugars = sugars;
		this.fibers = fibers;
		this.fat = fat;
		this.illnesses = illnesses;
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

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public double getWaterPercentage() {
		return waterPercentage;
	}

	public void setWaterPercentage(double waterPercentage) {
		this.waterPercentage = waterPercentage;
	}

	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	public double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public double getSugars() {
		return sugars;
	}

	public void setSugars(double sugars) {
		this.sugars = sugars;
	}

	public double getFibers() {
		return fibers;
	}

	public void setFibers(double fibers) {
		this.fibers = fibers;
	}

	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	public List<Illness> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(List<Illness> illnesses) {
		this.illnesses = illnesses;
	}
	
	public void addIllness(Illness illness) {
		this.illnesses.add(illness);
	}

//	public String getIllnessesString() {
//		return illnessesString;
//	}
//
//	public void setIllnessesString(String illnessesString) {
//		this.illnessesString = illnessesString;
//	}
//	public Meal getMeal() {
//		return meal;
//	}
//
//	public void setMeal(Meal meal) {
//		this.meal = meal;
//	}

	public List<IngredientAmount> getIngredientAmounts() {
		return ingredientAmounts;
	}

	public void setIngredientAmounts(List<IngredientAmount> ingredientAmounts) {
		this.ingredientAmounts = ingredientAmounts;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (calories != other.calories)
			return false;
		if (carbohydrates != other.carbohydrates)
			return false;
		if (fat != other.fat)
			return false;
		if (fibers != other.fibers)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proteins != other.proteins)
			return false;
		if (sugars != other.sugars)
			return false;
		if (waterPercentage != other.waterPercentage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", calories=" + calories + ", waterPercentage="
				+ waterPercentage + ", proteins=" + proteins + ", carbohydrates=" + carbohydrates + ", sugars=" + sugars
				+ ", fibers=" + fibers + ", fat=" + fat + "]";
	}
}
