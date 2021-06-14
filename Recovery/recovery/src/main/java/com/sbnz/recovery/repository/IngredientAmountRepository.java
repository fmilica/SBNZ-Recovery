package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.IngredientAmount;

@Repository
public interface IngredientAmountRepository  extends JpaRepository<IngredientAmount, Long>{

}
