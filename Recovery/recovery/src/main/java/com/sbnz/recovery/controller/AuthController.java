package com.sbnz.recovery.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sbnz.recovery.dto.PatientDTO;
import com.sbnz.recovery.dto.UserLoginDTO;
import com.sbnz.recovery.dto.UserTokenStateDTO;
import com.sbnz.recovery.helper.PatientMapper;
import com.sbnz.recovery.model.Patient;
import com.sbnz.recovery.model.User;
import com.sbnz.recovery.security.TokenUtils;
import com.sbnz.recovery.service.AuthService;
import com.sbnz.recovery.service.CustomUserDetailsService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService; 

	@Autowired
	private AuthenticationManager authenticationManager;
	
	private final PatientMapper patientMapper = new PatientMapper();
	

	@PostMapping("/log-in")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
			HttpServletResponse response) {

		try {
			boolean verified = true;

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			// Ubaci korisnika u trenutni security kontekst
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token za tog korisnika
			User user = (User) authentication.getPrincipal();

			String username = user.getUsername();
			String jwt = tokenUtils.generateToken(user.getUsername());
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, username, verified));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping( value = "/register")
	public ResponseEntity<PatientDTO> register(@Valid @RequestBody PatientDTO userDTO) {

		Patient patient = patientMapper.toEntity(userDTO);
		
		try {
			Patient createdUser = this.authService.register(patient);
			return new ResponseEntity<>(patientMapper.toDto(createdUser), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
}
