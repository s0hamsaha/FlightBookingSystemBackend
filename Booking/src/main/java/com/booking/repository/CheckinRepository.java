package com.booking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booking.entity.CheckinDetails;

public interface CheckinRepository extends MongoRepository<CheckinDetails,Long> {
	CheckinDetails getByPassengerId(Long passengerId);
}
