package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	List<Patient> findAll();
	
	Patient findOneByUsername(String username);
}
