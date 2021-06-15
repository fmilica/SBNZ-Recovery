package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.dto.ViewTherapyDTO;
import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.model.Therapy;

public class ViewTherapyMapper implements MapperInterface<Therapy, ViewTherapyDTO> {

	@Override
	public Therapy toEntity(ViewTherapyDTO dto) {
		return null;
	}

	@Override
	public ViewTherapyDTO toDto(Therapy entity) {
		return new ViewTherapyDTO(new TherapyDTO(entity.getId(), entity.getName(), entity.getTherapyType(), 
				entity.getMaximumMonthlyApplication(), entity.getTemperature(), entity.getIntensity()), 
				toNameListIllness(entity.getApplicableIllness()), toNameListInjuryType(entity.getApplicableInjury()));
	}

	@Override
	public List<Therapy> toEntityList(List<ViewTherapyDTO> dtoList) {
		return null;
	}

	@Override
	public List<ViewTherapyDTO> toDtoList(List<Therapy> entityList) {
		List<ViewTherapyDTO> therapyDTOs = new ArrayList<ViewTherapyDTO>();
		for (Therapy therapy : entityList) {
			therapyDTOs.add(toDto(therapy));
		}
		return therapyDTOs;
	}

	private List<String> toNameListIllness(List<Illness> illnesses) {
		List<String> namesList = illnesses.stream()
                .map(Illness::getName)
                .collect(Collectors.toList());
		return namesList;
	}
	
	private List<String> toNameListInjuryType(List<InjuryType> injuryTypes) {
		List<String> namesList = injuryTypes.stream()
                .map(InjuryType::getName)
                .collect(Collectors.toList());
		return namesList;
	}
}
