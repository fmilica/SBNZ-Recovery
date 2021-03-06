package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

	List<Ingredient> findAll();
	
	Ingredient findOneById(Long id);
}
