package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.model.Meal;

public class MealMapper implements MapperInterface<Meal, MealDTO>{

	private final IngredientAmountMapper iaMapper = new IngredientAmountMapper();
	
	@Override
	public Meal toEntity(MealDTO dto) {
		return new Meal(dto.getName(), iaMapper.toEntityList(dto.getIngredients()), dto.getMealDescription());
	}

	@Override
	public MealDTO toDto(Meal entity) {
		return new MealDTO(entity.getName(), iaMapper.toDtoList(entity.getIngredients()), entity.getMealDescription(), 
				entity.getTotalCalories());
	}

	@Override
	public List<Meal> toEntityList(List<MealDTO> dtoList) {
		List<Meal> meals = new ArrayList<Meal>();
		for (MealDTO mealDto : dtoList) {
			meals.add(toEntity(mealDto));
		}
		return meals;
	}

	@Override
	public List<MealDTO> toDtoList(List<Meal> entityList) {
		List<MealDTO> mealDTOs = new ArrayList<MealDTO>();
		for (Meal meal : entityList) {
			mealDTOs.add(toDto(meal));
		}
		return mealDTOs;
	}

}
