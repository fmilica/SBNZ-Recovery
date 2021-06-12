package com.sbnz.recovery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.model.InjuryType;
import com.sbnz.recovery.repository.InjuryTypeRepository;

@Service
public class InjuryTypeService {

	@Autowired
	private InjuryTypeRepository injuryTypeRepository;
	
	public List<InjuryType> findAll() {
		return injuryTypeRepository.findAll();
	}
}
