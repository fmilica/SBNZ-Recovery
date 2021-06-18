package com.sbnz.recovery.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sbnz.recovery.exceptions.ExistingFieldValueException;
import com.sbnz.recovery.model.ChosenPatient;
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
		if (existingUser != null) {
			throw new ExistingFieldValueException("User", "email");
		}
		//enkripcija lozinke
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		// postavljanje authorities
		user.setAuthorities(new HashSet<>(authorityService.findByName("ROLE_PATIENT")));

		Patient patient = patientRepository.save(user);
		
		rulesSession.getAgenda().getAgendaGroup("bmr-regular-calorie").setFocus();
		rulesSession.insert(patient);
		rulesSession.insert(new ChosenPatient(patient.getId()));
		rulesSession.fireAllRules();
		
		return patientRepository.save(patient);
	}

	public boolean checkSusUser(String username) {
		QueryResults results = cepSession.getQueryResults("getSuspiciousUserEventByUsername", username);
		List<Object> susUsers = new ArrayList<>();
		
		for (QueryResultsRow row : results) {
			Object susUser = (Object) row.get("$susUser");
			susUsers.add(susUser);
		}
		
		if (susUsers.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean loginFailed(String username) {
		LoginEvent event = new LoginEvent(username);
		cepSession.insert(event);
		int fired = cepSession.fireAllRules();
		if (fired != 0) {
			return true;
		}
		return false;
	}
}
