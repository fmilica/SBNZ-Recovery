package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbnz.recovery.dto.IngredientAmountDTO;
import com.sbnz.recovery.model.IngredientAmount;

public class IngredientAmountMapper implements MapperInterface<IngredientAmount, IngredientAmountDTO>{

	private final IngredientMapper iMapper = new IngredientMapper();
	
	@Override
	public IngredientAmount toEntity(IngredientAmountDTO dto) {
		return new IngredientAmount(iMapper.toEntity(dto.getIngredient()), dto.getAmount());
	}

	@Override
	public IngredientAmountDTO toDto(IngredientAmount entity) {
		return new IngredientAmountDTO(iMapper.toDto(entity.getIngredient()), entity.getAmount());
	}

	@Override
	public List<IngredientAmount> toEntityList(List<IngredientAmountDTO> dtoList) {
		List<IngredientAmount> ias = new ArrayList<IngredientAmount>();
		for (IngredientAmountDTO iaDto : dtoList) {
			ias.add(toEntity(iaDto));
		}
		return ias;
	}

	@Override
	public List<IngredientAmountDTO> toDtoList(List<IngredientAmount> entityList) {
		List<IngredientAmountDTO> iaDTOs = new ArrayList<IngredientAmountDTO>();
		for (IngredientAmount ingredientAmount : entityList) {
			iaDTOs.add(toDto(ingredientAmount));
		}
		return iaDTOs;
	}
	
	public List<IngredientAmountDTO> toDtoList(Set<IngredientAmount> entityList) {
		List<IngredientAmountDTO> iaDTOs = new ArrayList<IngredientAmountDTO>();
		for (IngredientAmount ingredientAmount : entityList) {
			iaDTOs.add(toDto(ingredientAmount));
		}
		return iaDTOs;
	}
	
	public Set<IngredientAmount> toEntitySet(List<IngredientAmountDTO> dtoList) {
		Set<IngredientAmount> ias = new HashSet<IngredientAmount>();
		for (IngredientAmountDTO iaDto : dtoList) {
			ias.add(toEntity(iaDto));
		}
		return ias;
	}

}
