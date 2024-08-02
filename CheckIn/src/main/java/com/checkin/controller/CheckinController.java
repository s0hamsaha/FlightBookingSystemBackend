package com.checkin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkin.entity.CheckinDetails;
import com.checkin.service.CheckinServiceImpl;

@RestController
@RequestMapping("/checkin")
@CrossOrigin(origins="http://localhost:4200")
public class CheckinController {
	@Autowired
	private CheckinServiceImpl checkinServiceImpl;
	
	@PostMapping("/createCheckin/{passengerId}/{bookingId}")
	public ResponseEntity<CheckinDetails> createCheckIn(@RequestBody CheckinDetails checkinDetails,@PathVariable Long passengerId,@PathVariable Long bookingId)
	{
		return ResponseEntity.ok(checkinServiceImpl.createCheckIn(checkinDetails, passengerId,bookingId));
	}
	@GetMapping("/getCheckinDetails")
	public ResponseEntity<List<CheckinDetails>> getAllCheckins()
	{
		return ResponseEntity.ok(checkinServiceImpl.getAllCheckInDetails());
	}
	@GetMapping("/getCheckinDetailsByCheckInId/{checkinId}")
	public ResponseEntity<CheckinDetails> getCheckInDetailsByCheckInId(@PathVariable Long checkinId)
	{
		return ResponseEntity.ok(checkinServiceImpl.getCheckinDetailById(checkinId));
	}
	@GetMapping("/getCheckinDetailsByPassengerId/{passengerId}")
	public ResponseEntity<CheckinDetails> getCheckInDetailsByPassengerId(@PathVariable Long passengerId)
	{
		return ResponseEntity.ok(checkinServiceImpl.getCheckinDetailByPassengerId(passengerId));
	}
}
