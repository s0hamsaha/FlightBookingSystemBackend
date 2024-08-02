package com.flightsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.flightsecurity.config.CustomUserDetails;
import com.flightsecurity.entity.UserCredentials;
import com.flightsecurity.repository.UserCredentialsRepository;

@Component
public class CustomUserDetailsService  implements UserDetailsService{

	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional <UserCredentials> credential=userCredentialsRepository.findByUserName(username);
		return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found with name :"+username));
	}
}
