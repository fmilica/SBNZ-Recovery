package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.IngredientDTO;
import com.sbnz.recovery.model.Ingredient;

public class IngredientMapper implements MapperInterface<Ingredient, IngredientDTO>{
	
	private final IllnessMapper iMapper = new IllnessMapper();

	@Override
	public Ingredient toEntity(IngredientDTO dto) {
		return new Ingredient(dto.getId(), dto.getName(), dto.getCalories(), dto.getWaterPercentage(), 
				dto.getProteins(), dto.getCarbohydrates(), dto.getSugars(), dto.getFibers(), dto.getFat());
	}

	@Override
	public IngredientDTO toDto(Ingredient entity) {
		return new IngredientDTO(entity.getId(), entity.getName(), entity.getCalories(), entity.getWaterPercentage(), 
				entity.getProteins(), entity.getCarbohydrates(), entity.getSugars(), entity.getFibers(), entity.getFat(),
				iMapper.toDtoList(entity.getIllnesses()));
	}

	@Override
	public List<Ingredient> toEntityList(List<IngredientDTO> dtoList) {
		List<Ingredient> igredients = new ArrayList<Ingredient>();
		for (IngredientDTO ingredientDto : dtoList) {
			igredients.add(toEntity(ingredientDto));
		}
		return igredients;
	}

	@Override
	public List<IngredientDTO> toDtoList(List<Ingredient> entityList) {
		List<IngredientDTO> igredientDTOs = new ArrayList<IngredientDTO>();
		for (Ingredient ingredient : entityList) {
			igredientDTOs.add(toDto(ingredient));
		}
		return igredientDTOs;
	}

}
