package com.booking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booking.entity.Passengers;

public interface PassengerRepository extends MongoRepository<Passengers,Long> {
	List<Passengers> getByBookingId(Long bookingId);
}