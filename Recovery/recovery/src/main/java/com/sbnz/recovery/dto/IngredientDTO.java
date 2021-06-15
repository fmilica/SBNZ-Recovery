package com.sbnz.recovery.dto;

import java.util.ArrayList;
import java.util.List;

public class IngredientDTO {

	private Long id;
	private String name;
	private double calories;
	private double waterPercentage;
	private double proteins;
	private double carbohydrates;
	private double sugars;
	private double fibers;
	private double fat;
	private List<IllnessDTO> illnesses;
	
	public IngredientDTO () {
		this.illnesses = new ArrayList<IllnessDTO>();
	}

	public IngredientDTO(Long id, String name, double calories, double waterPercentage, double proteins, double carbohydrates,
			double sugars, double fibers, double fat, List<IllnessDTO> illnesses) {
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

	public List<IllnessDTO> getIllnesses() {
		return illnesses;
	}

	public void setIllnesses(List<IllnessDTO> illnesses) {
		this.illnesses = illnesses;
	}
}
