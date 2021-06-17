package com.sbnz.recovery.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyMealDTO {

	private Long id;
	private Date day;
	private List<MealDTO> meals;
	
	public DailyMealDTO() {
		this.meals = new ArrayList<MealDTO>();
	}

	public DailyMealDTO(Long id, Date day, List<MealDTO> meals) {
		this.id = id;
		this.day = day;
		this.meals = meals;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public List<MealDTO> getMeals() {
		return meals;
	}

	public void setMeals(List<MealDTO> meals) {
		this.meals = meals;
	}
}
