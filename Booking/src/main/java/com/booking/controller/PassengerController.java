package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.booking.entity.Passengers;
import com.booking.service.PassengerServiceImpl;

@RestController
@RequestMapping("/passenger")
@CrossOrigin(origins = "http://localhost:4200")
public class PassengerController {
	
	@Autowired
	private PassengerServiceImpl passengerServiceImpl;
	@GetMapping
	public ResponseEntity<List<Passengers>> getAllPassengers()
	{
		return ResponseEntity.ok(passengerServiceImpl.getAllPassengers());
	}
}
