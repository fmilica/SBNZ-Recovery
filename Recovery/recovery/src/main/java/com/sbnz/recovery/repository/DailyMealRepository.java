package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.DailyMeal;

@Repository
public interface DailyMealRepository extends JpaRepository<DailyMeal, Long> {

	List<DailyMeal> findAll();
	
	DailyMeal findOneById(Long id);
	
	DailyMeal findOneByPatientId(Long patientId);
}
