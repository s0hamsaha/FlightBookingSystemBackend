package com.flightsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightsecurity.entity.UserCredentials;
import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer> {

	Optional<UserCredentials> findByUserName(String username);

}
