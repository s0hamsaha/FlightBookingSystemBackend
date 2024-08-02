package com.checkin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.checkin.entity.FlightDetails;

@Service
@FeignClient(name="FLIGHTDETAILS")
public interface FlightClient {

	@GetMapping("/flights/getByFlightId/{id}")
	FlightDetails getFlightOfBooking(@PathVariable int id);
	@PutMapping("/flights/updateSeatIncrementor/{id}")
	FlightDetails updateSeatIncrementor(@RequestBody FlightDetails flightDetails,@PathVariable int id);
}
