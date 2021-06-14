package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.model.Therapy;

public class TherapyMapper implements MapperInterface<Therapy, TherapyDTO>{

	@Override
	public Therapy toEntity(TherapyDTO dto) {
		return new Therapy(dto.getName(), dto.getTherapyType(), dto.getMaximumMonthlyApplication(), dto.getTemperature(), dto.getIntensity());
	}

	@Override
	public TherapyDTO toDto(Therapy entity) {
		return new TherapyDTO(entity.getId(), entity.getName(), entity.getTherapyType(), entity.getMaximumMonthlyApplication(), entity.getTemperature(), entity.getIntensity());
	}

	@Override
	public List<Therapy> toEntityList(List<TherapyDTO> dtoList) {
		List<Therapy> therapys = new ArrayList<Therapy>();
		for (TherapyDTO therapyDto : dtoList) {
			therapys.add(toEntity(therapyDto));
		}
		return therapys;
	}

	@Override
	public List<TherapyDTO> toDtoList(List<Therapy> entityList) {
		List<TherapyDTO> therapyDTOs = new ArrayList<TherapyDTO>();
		for (Therapy therapy : entityList) {
			therapyDTOs.add(toDto(therapy));
		}
		return therapyDTOs;
	}

}
