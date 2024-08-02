package com.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.entity.Bookings;
import com.booking.service.BookingServiceImpl;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	
	@PostMapping("/newbooking")
	public ResponseEntity<Bookings> addNewBooking(@RequestBody Bookings bookings,@RequestParam("flightId") int flightId)
	{
		return ResponseEntity.ok(bookingServiceImpl.addBooking(bookings,flightId));
	}
	@GetMapping
	public ResponseEntity<List<Bookings>> getAllBookings()
	{
		return ResponseEntity.ok(bookingServiceImpl.getAllBookings());
	}
	@GetMapping("/getByBookingId/{bookingId}")
	public ResponseEntity<Bookings> getByBookingId(@PathVariable Long bookingId)
	{
		return ResponseEntity.ok(bookingServiceImpl.getByBookingId(bookingId));
	}
	@GetMapping("/checkBookingIdAvailable/{bookingId}")
	public ResponseEntity<Boolean> checkBookingIdPresent(@PathVariable Long bookingId)
	{
		return ResponseEntity.ok(bookingServiceImpl.isBookingIdAvailable(bookingId));
	}
	@GetMapping("/getBookingsByEmail/{email}")
	public ResponseEntity<List<Bookings>> getBokingsByEmail(@PathVariable String email)
	{
		return ResponseEntity.ok(bookingServiceImpl.getBookingsByEmail(email));
	}
}
