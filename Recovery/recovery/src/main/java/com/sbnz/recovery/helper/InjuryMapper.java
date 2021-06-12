package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.InjuryDTO;
import com.sbnz.recovery.model.Injury;

public class InjuryMapper implements MapperInterface<Injury, InjuryDTO> {

	private final InjuryTypeMapper injuryTypeMapper = new InjuryTypeMapper();
	
	@Override
	public Injury toEntity(InjuryDTO dto) {
		return new Injury(dto.getId(), dto.getName(), dto.getStartDate(), dto.getEndDate(), 
				dto.getDescription(), injuryTypeMapper.toEntity(dto.getInjuryTypeDto()), dto.getInjuryBodyPart());
	}

	@Override
	public InjuryDTO toDto(Injury entity) {
		return new InjuryDTO(entity.getId(), entity.getName(), entity.getStartDate(), entity.getEndDate(), 
				entity.getDescription(), injuryTypeMapper.toDto(entity.getInjuryType()), entity.getInjuryBodyPart());
	}

	@Override
	public List<Injury> toEntityList(List<InjuryDTO> dtoList) {
		if (dtoList == null) {
			return null;
		}
		List<Injury> injuryList = new ArrayList<Injury>();
		for (InjuryDTO injuryDto : dtoList) {
			injuryList.add(toEntity(injuryDto));
		}
		return injuryList;
	}

	@Override
	public List<InjuryDTO> toDtoList(List<Injury> entityList) {
		if (entityList == null) {
			return null;
		}
		List<InjuryDTO> dtoList = new ArrayList<InjuryDTO>();
		for (Injury injury : entityList) {
			dtoList.add(toDto(injury));
		}
		return dtoList;
	}

}
