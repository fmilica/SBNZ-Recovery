package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.sbnz.recovery.dto.DailyMealDTO;
import com.sbnz.recovery.dto.MealDTO;
import com.sbnz.recovery.model.DailyMeal;
import com.sbnz.recovery.model.Meal;

public class DailyMealMapper implements MapperInterface<DailyMeal, DailyMealDTO>{

	private final MealMapper mealMapper = new MealMapper();
	
	@Override
	public DailyMeal toEntity(DailyMealDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DailyMealDTO toDto(DailyMeal entity) {
		return new DailyMealDTO(entity.getId(), entity.getDay(), mealMapper.toDtoList(entity.getMeals()));
	}

	@Override
	public List<DailyMeal> toEntityList(List<DailyMealDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DailyMealDTO> toDtoList(List<DailyMeal> entityList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<DailyMealDTO> toDtoList(Set<DailyMeal> entityList) {
		List<DailyMealDTO> dailyMealDTOs = new ArrayList<DailyMealDTO>();
		for (DailyMeal dailyMeal : entityList) {
			dailyMealDTOs.add(toDto(dailyMeal));
		}
		return dailyMealDTOs;
	}

}
