package com.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booking.entity.Bookings;
import java.util.List;


public interface BookingRepository extends MongoRepository<Bookings,Long> {

	List<Bookings> getByEmail(String email);
}
