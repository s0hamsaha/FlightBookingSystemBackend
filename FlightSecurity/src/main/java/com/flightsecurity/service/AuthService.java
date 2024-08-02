package com.flightsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightsecurity.entity.UserCredentials;
import com.flightsecurity.exception.NoUserFoundException;
import com.flightsecurity.repository.UserCredentialsRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public UserCredentials addUser(UserCredentials userCredentials)
	{ 
		Optional<UserCredentials> uCredentials =userCredentialsRepository.findByUserName(userCredentials.getUserName());
		if(uCredentials.isEmpty())
		{
			userCredentials.setPassword(passwordEncoder.encode(userCredentials.getPassword()));
			if(userCredentials.getRole()==null)
				userCredentials.setRole("USER");
			return userCredentialsRepository.save(userCredentials);
		}
		else {
			throw new NoUserFoundException("User with provided Email-Id exists");
		}
	}
	
	public String generateToken(String userName, String role)
	{
		return jwtService.generateToken(userName,role);
	}
	
	public void validateToken(String token)
	{
		jwtService.validateToken(token);
	}
	
}
