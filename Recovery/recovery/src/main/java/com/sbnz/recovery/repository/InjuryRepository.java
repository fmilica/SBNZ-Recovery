package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.Injury;

public interface InjuryRepository extends JpaRepository<Injury, Long> {

	List<Injury> findAllByPatientIdOrderByStartDateDesc(Long patientId);
}
