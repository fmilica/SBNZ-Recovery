package com.sbnz.recovery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.model.Illness;
import com.sbnz.recovery.repository.IllnessRepository;

@Service
public class IllnessService {

	@Autowired
	private IllnessRepository illnessRepository;
	
	public List<Illness> findAll() {
		return illnessRepository.findAll();
	}
	
	public Illness save(Illness i) {
		return illnessRepository.save(i);
	}
}
