package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sbnz.recovery.dto.IllnessDTO;
import com.sbnz.recovery.model.Illness;

public class IllnessMapper implements MapperInterface<Illness, IllnessDTO> {

	
	@Override
	public Illness toEntity(IllnessDTO dto) {
		return new Illness(dto.getId(), dto.getName());
	}

	@Override
	public IllnessDTO toDto(Illness entity) {
		return new IllnessDTO(entity.getId(), entity.getName());
	}

	@Override
	public List<Illness> toEntityList(List<IllnessDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		List<Illness> illnessList = new ArrayList<Illness>();
		for (IllnessDTO illnessDto : dtoList) {
			illnessList.add(toEntity(illnessDto));
		}
		return illnessList;
	}

	@Override
	public List<IllnessDTO> toDtoList(List<Illness> entityList) {
		if (entityList == null) {
			return null;
		}
		List<IllnessDTO> dtoList = new ArrayList<IllnessDTO>();
		for (Illness illness : entityList) {
			dtoList.add(toDto(illness));
		}
		return dtoList;
	}
	
	public Set<Illness> toEntitySet(List<IllnessDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		Set<Illness> illnessList = new HashSet<Illness>();
		for (IllnessDTO illnessDto : dtoList) {
			illnessList.add(toEntity(illnessDto));
		}
		return illnessList;
	}
	
	public List<IllnessDTO> toDtoList(Set<Illness> entityList) {
		if (entityList == null) {
			return null;
		}
		List<IllnessDTO> dtoList = new ArrayList<IllnessDTO>();
		for (Illness illness : entityList) {
			dtoList.add(toDto(illness));
		}
		return dtoList;
	}

}
