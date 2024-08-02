package com.FlightDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication
@Validated
@EnableDiscoveryClient
public class FlightDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightDetailsApplication.class, args);
	}

}
