package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.Injury;

public interface InjuryRepository extends JpaRepository<Injury, Long> {

}
