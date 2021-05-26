package com.sbnz.recovery.dto;

public class IngredientDTO {

	private String name;
	private double calories;
	private double waterPercentage;
	private double proteins;
	private double carbohydrates;
	private double sugars;
	private double fibers;
	private double fat;
	
	public IngredientDTO () {}

	public IngredientDTO(String name, double calories, double waterPercentage, double proteins, double carbohydrates,
			double sugars, double fibers, double fat) {
		super();
		this.name = name;
		this.calories = calories;
		this.waterPercentage = waterPercentage;
		this.proteins = proteins;
		this.carbohydrates = carbohydrates;
		this.sugars = sugars;
		this.fibers = fibers;
		this.fat = fat;
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
}
