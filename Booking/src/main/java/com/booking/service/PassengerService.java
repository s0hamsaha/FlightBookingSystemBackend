package com.booking.service;

import java.util.List;

import com.booking.entity.Passengers;

public interface PassengerService {
	Passengers addPassenger(Passengers passengers);
	List<Passengers> getAllPassengers();
	Passengers getByPassengerId(Long passengerId);
	List<Passengers> getByBookingId(Long bookingId);
}
