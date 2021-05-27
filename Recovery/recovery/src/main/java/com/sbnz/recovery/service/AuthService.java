package com.sbnz.recovery.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.User;
import com.sbnz.recovery.repository.PatientRepository;
import com.sbnz.recovery.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private KnowledgeSessionService kieSessionService;

	public Patient register(Patient user) throws Exception {
		User existingUser = (User) userRepository.findByUsername(user.getUsername());
		if (existingUser == null) {
			//enkripcija lozinke
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			// postavljanje authorities
			user.setAuthorities(new ArrayList<>(authorityService.findByName("ROLE_USER")));
			
			return patientRepository.save(user);
		}

		throw new ExistingFieldValueException("User", "email");
	}

}
