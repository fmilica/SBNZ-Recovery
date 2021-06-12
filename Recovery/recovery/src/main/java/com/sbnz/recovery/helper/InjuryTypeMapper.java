package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.InjuryTypeDTO;
import com.sbnz.recovery.model.InjuryType;

public class InjuryTypeMapper implements MapperInterface<InjuryType, InjuryTypeDTO> {

	@Override
	public InjuryType toEntity(InjuryTypeDTO dto) {
		return new InjuryType(dto.getId(), dto.getName());
	}

	@Override
	public InjuryTypeDTO toDto(InjuryType entity) {
		return new InjuryTypeDTO(entity.getId(), entity.getName());
	}

	@Override
	public List<InjuryType> toEntityList(List<InjuryTypeDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		List<InjuryType> injuryList = new ArrayList<InjuryType>();
		for (InjuryTypeDTO injuryDto : dtoList) {
			injuryList.add(toEntity(injuryDto));
		}
		return injuryList;
	}

	@Override
	public List<InjuryTypeDTO> toDtoList(List<InjuryType> entityList) {
		if (entityList == null) {
			return null;
		}
		List<InjuryTypeDTO> dtoList = new ArrayList<InjuryTypeDTO>();
		for (InjuryType injury : entityList) {
			dtoList.add(toDto(injury));
		}
		return dtoList;
	}

}
