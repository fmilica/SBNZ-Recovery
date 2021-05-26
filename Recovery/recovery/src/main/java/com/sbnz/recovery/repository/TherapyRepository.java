package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.Therapy;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long> {

}
