package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

	List<Meal> findAll();
	
	Meal findOneById(Long id);
}
