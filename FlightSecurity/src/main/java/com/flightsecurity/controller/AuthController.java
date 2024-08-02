package com.flightsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightsecurity.entity.UserCredentials;
import com.flightsecurity.entity.UserCredentialsRes;
import com.flightsecurity.repository.UserCredentialsRepository;
import com.flightsecurity.service.AuthService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;

	@PostMapping("/registerUser")
	public UserCredentials addNewUser(@RequestBody UserCredentials userCredentials) {
		return authService.addUser(userCredentials);
	}

	@PostMapping("/login")
	public UserCredentialsRes getToken(@RequestBody UserCredentials userCredentials) {
		UserCredentialsRes userCredentialsRes = new UserCredentialsRes();
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userCredentials.getUserName(), userCredentials.getPassword()));
		if (authenticate.isAuthenticated()) {
			String token= authService.generateToken(userCredentials.getUserName(),
					AuthorityUtils.authorityListToSet(authenticate.getAuthorities()).iterator().next());
			UserCredentials userCredentials2=userCredentialsRepository.findByUserName(userCredentials.getUserName()).get();
			userCredentialsRes.setFname(userCredentials2.getFirstName());
			userCredentialsRes.setLname(userCredentials2.getLastName());
			userCredentialsRes.setEmail(userCredentials2.getUserName());
			userCredentialsRes.setPhoneNumber(userCredentials2.getPhoneNumber());
			userCredentialsRes.setToken(token);
			return userCredentialsRes;
		} else
			throw new RuntimeException("Invalid User");
	}

	
	  @GetMapping("/validateToken") 
	  public String validateToken(@RequestParam("token") String token) {
	  authService.validateToken(token); return "Token is valid"; 
	  }
}
