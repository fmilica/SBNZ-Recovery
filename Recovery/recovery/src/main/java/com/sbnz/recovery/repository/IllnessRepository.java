package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.Illness;

public interface IllnessRepository extends JpaRepository<Illness, Long> {

}
