package com.FlightDetails.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FlightDetails.entity.FlightDetails;
import com.FlightDetails.service.FlightDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flights")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class FlightDetailsController {
	
	Logger logger=LoggerFactory.getLogger(FlightDetailsController.class);
	
	@Autowired
	private FlightDetailsServiceImpl flightDetailsServiceImpl;
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addFlight")
	public ResponseEntity<FlightDetails> create(@Valid @RequestBody FlightDetails flightDetails)
	{	
		logger.info("New Flight Added");
		return ResponseEntity.ok(flightDetailsServiceImpl.addDetails(flightDetails));
	}
	
	@GetMapping
	public ResponseEntity<List<FlightDetails>> getFlightDetails(){
		logger.info("Fetched all Flight Details");
		return ResponseEntity.ok(flightDetailsServiceImpl.getAllDetails());
	}
	
	@GetMapping("/getByFlightId/{id}")
	public ResponseEntity<FlightDetails> getFlightByFlightId(@PathVariable int id)
	{
		logger.info("Fetched flight details with flightId "+id);
		return ResponseEntity.ok(flightDetailsServiceImpl.getFlightByFlightId(id));
	}
	
	@PutMapping("/updateByFlightId/{id}")
	public ResponseEntity<FlightDetails> updateByFlightId(@RequestBody FlightDetails flightDetails, @PathVariable Integer id)
	{
		logger.info("Updated flight details with flightId "+id);
		return ResponseEntity.ok(flightDetailsServiceImpl.updatebyID(flightDetails, id));
	}
	@PutMapping("/updatenoofseats/{flightId}")
	public ResponseEntity<FlightDetails> updateSeats(@RequestBody FlightDetails flightDetails,@PathVariable int flightId)
	{
		return ResponseEntity.ok(flightDetailsServiceImpl.updatebyID(flightDetails,flightId));
	}
	@PutMapping("/updateSeatIncrementor/{flightId}")
	public ResponseEntity<FlightDetails> updateSeatIncrementor(@RequestBody FlightDetails flightDetails,@PathVariable int flightId)
	{
		return ResponseEntity.ok(flightDetailsServiceImpl.updatebyID(flightDetails,flightId));
	}
	
	@DeleteMapping("/deleteByFlightId/{id}")
	public ResponseEntity<FlightDetails> deleteByFlightId(@PathVariable Integer id)
	{
		logger.info("Deleted flight details with flightId "+id);
		return ResponseEntity.ok(flightDetailsServiceImpl.deleteByID(id));
	}
	
}
