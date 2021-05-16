package com.sbnz.recovery.model;

import java.io.Serializable;

public class Nutrition implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private int calories;
	private int waterPercentage;
	private int proteins;
	private int carbohydrates;
	private int sugars;
	private int fibers; //uopste nisam sigurna dal se ovako kaze
	private int fat;
	
	public Nutrition() {
		super();
	}

	public Nutrition(String name, int calories, int waterPercentage, int proteins, int carbohydrates, int sugars,
			int fibers, int fat) {
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

	public Nutrition(Long id, String name, int calories, int waterPercentage, int proteins, int carbohydrates,
			int sugars, int fibers, int fat) {
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

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public int getWaterPercentage() {
		return waterPercentage;
	}

	public void setWaterPercentage(int waterPercentage) {
		this.waterPercentage = waterPercentage;
	}

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public int getSugars() {
		return sugars;
	}

	public void setSugars(int sugars) {
		this.sugars = sugars;
	}

	public int getFibers() {
		return fibers;
	}

	public void setFibers(int fibers) {
		this.fibers = fibers;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}
}
