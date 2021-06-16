package com.sbnz.recovery.helper;

import java.util.ArrayList;
import java.util.List;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.model.Patient;

public class PatientMapper implements MapperInterface<Patient, PatientDTO>{

	private final IllnessMapper illnessMapper = new IllnessMapper();
	
	@Override
	public Patient toEntity(PatientDTO dto) {
		return new Patient(dto.getEmail(), dto.getPassword(), dto.getName(), dto.getSurname(), dto.getGender(),
				dto.getDateOfBirth(), dto.getHeight(), dto.getWeight(), dto.getPhysicalActivity(), 
				illnessMapper.toEntitySet(dto.getAnamnesis()));
	}

	@Override
	public PatientDTO toDto(Patient entity) {
		return new PatientDTO(entity.getId(), entity.getUsername(), entity.getName(), entity.getSurname(), entity.getGender(), 
				entity.getDateOfBirth(), entity.getHeight(), entity.getWeight(), 
				entity.getPhysicalActivityBeforeInjury(), illnessMapper.toDtoList(entity.getAnamnesis()), 
				entity.getPhysicalActivityAfterInjury(), entity.getRegularDailyCaloryIntake(), 
				entity.getDailyCaloryIntakeAfterInjury());
	}

	@Override
	public List<Patient> toEntityList(List<PatientDTO> dtoList) {
		List<Patient> patients = new ArrayList<Patient>();
		for(PatientDTO patientDto: dtoList) {
			patients.add(toEntity(patientDto));
		}
		return patients;
	}

	@Override
	public List<PatientDTO> toDtoList(List<Patient> entityList) {
		List<PatientDTO> patientDTOs = new ArrayList<PatientDTO>();
		for(Patient patient: entityList) {
			patientDTOs.add(toDto(patient));
		}
		return patientDTOs;
	}

}
