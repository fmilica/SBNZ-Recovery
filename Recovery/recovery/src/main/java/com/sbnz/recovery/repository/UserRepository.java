package com.sbnz.recovery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbnz.recovery.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
