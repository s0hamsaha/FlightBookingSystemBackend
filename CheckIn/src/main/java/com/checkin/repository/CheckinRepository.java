package com.checkin.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.checkin.entity.CheckinDetails;



public interface CheckinRepository extends MongoRepository<CheckinDetails,Long> {
	CheckinDetails getByPassengerId(Long passengerId);
}
