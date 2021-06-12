package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.InjuryType;

public interface InjuryTypeRepository extends JpaRepository<InjuryType, Long> {

}
