package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.AppliedTherapyDTO;
import com.sbnz.recovery.dto.TherapyDTO;
import com.sbnz.recovery.model.AppliedTherapy;
import com.sbnz.recovery.model.Therapy;

public class AppliedTherapyMapper implements MapperInterface<AppliedTherapy, AppliedTherapyDTO> {

	@Override
	public AppliedTherapy toEntity(AppliedTherapyDTO dto) {
		return new AppliedTherapy(dto.getId(), dto.getApplicationDate(), 
				new Therapy(dto.getTherapy().getId()));
	}

	@Override
	public AppliedTherapyDTO toDto(AppliedTherapy entity) {
		return new AppliedTherapyDTO(entity.getId(), entity.getApplicationDate(), 
				new TherapyDTO(entity.getTherapy().getId(), entity.getTherapy().getName(),
						entity.getTherapy().getTherapyType()));
	}

	@Override
	public List<AppliedTherapy> toEntityList(List<AppliedTherapyDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		List<AppliedTherapy> appliedTherapyList = new ArrayList<AppliedTherapy>();
		for (AppliedTherapyDTO appliedTherapyDto : dtoList) {
			appliedTherapyList.add(toEntity(appliedTherapyDto));
		}
		return appliedTherapyList;
	}

	@Override
	public List<AppliedTherapyDTO> toDtoList(List<AppliedTherapy> entityList) {
		if (entityList == null) {
			return null;
		}
		List<AppliedTherapyDTO> dtoList = new ArrayList<AppliedTherapyDTO>();
		for (AppliedTherapy appliedTherapy : entityList) {
			dtoList.add(toDto(appliedTherapy));
		}
		return dtoList;
	}

}
