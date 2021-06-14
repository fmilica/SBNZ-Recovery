package com.sbnz.recovery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.AppliedTherapy;

@Repository
public interface AppliedTherapyRepository extends JpaRepository<AppliedTherapy, Long>{

	List<AppliedTherapy> findAllByInjuryPatientUsername(String username);
	
	List<AppliedTherapy> findAllByInjuryIdOrderByApplicationDateDesc(Long injuryId);
}
