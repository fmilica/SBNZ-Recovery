package com.sbnz.recovery.dto;

public class IngredientAmountDTO {
	
	private IngredientDTO ingredient;
	private double amount;
	
	public IngredientAmountDTO() {
		super();
	}

	public IngredientAmountDTO(IngredientDTO ingredient, double amount) {
		super();
		this.ingredient = ingredient;
		this.amount = amount;
	}

	public IngredientDTO getIngredient() {
		return ingredient;
	}

	public void setIngredient(IngredientDTO ingredient) {
		this.ingredient = ingredient;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
