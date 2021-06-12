package com.sbnz.recovery.service;

import java.util.ArrayList;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.User;
import com.sbnz.recovery.model.events.LoginEvent;
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
	@Qualifier(value = "rulesSession")
	private KieSession rulesSession;
	
	@Autowired
	@Qualifier(value = "cepSession")
	private KieSession cepSession;

	public Patient register(Patient user) throws ExistingFieldValueException {
		User existingUser = (User) userRepository.findByUsername(user.getUsername());
		if (existingUser == null) {
			//enkripcija lozinke
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			// postavljanje authorities
			user.setAuthorities(new ArrayList<>(authorityService.findByName("ROLE_USER")));

			rulesSession.setGlobal("currentPatient", user.getUsername());
			rulesSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
			rulesSession.insert(user);
			rulesSession.fireAllRules();
			
			return patientRepository.save(user);
		}

		throw new ExistingFieldValueException("User", "email");
	}

	public void loginFailed(String username) {
		LoginEvent event = new LoginEvent(username);
		cepSession.insert(event);
		cepSession.fireAllRules();
	}
}
