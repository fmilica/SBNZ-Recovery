package com.sbnz.recovery.model;

import java.io.Serializable;

public class Meal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Nutrition nutrition;
	private String mealDescription;
	
}
