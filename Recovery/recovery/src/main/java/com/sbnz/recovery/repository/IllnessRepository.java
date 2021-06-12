package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbnz.recovery.model.Illness;

@Repository
public interface IllnessRepository extends JpaRepository<Illness, Long> {

}
