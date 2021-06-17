package com.sbnz.recovery.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.DailyMeal;

@Repository
public interface DailyMealRepository extends JpaRepository<DailyMeal, Long> {

	List<DailyMeal> findAll();
	
	DailyMeal findOneById(Long id);
	
	DailyMeal findOneByPatientId(Long patientId);
	
	DailyMeal findOneByPatientIdAndDay(Long patientId, Date CURRENT_DATE);
	
	DailyMeal findOneByPatientIdAndDayAndMealsId(Long patientId, Date CURRENT_DATE, Long id);
}
