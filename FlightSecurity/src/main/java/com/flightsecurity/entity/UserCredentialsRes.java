package com.flightsecurity.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCredentialsRes {

	private String fname;
	private String lname;
	private String email;
	private String phoneNumber;
	private String token;
}
