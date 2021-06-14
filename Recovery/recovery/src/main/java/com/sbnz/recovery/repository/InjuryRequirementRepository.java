package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.InjuryRequirement;

public interface InjuryRequirementRepository extends JpaRepository<InjuryRequirement, Long> {

}
