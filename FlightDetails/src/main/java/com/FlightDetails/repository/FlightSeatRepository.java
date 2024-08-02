package com.FlightDetails.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FlightDetails.entity.*;

@Repository
public interface FlightSeatRepository extends JpaRepository<FlightSeats,Integer> {
	List<FlightSeats> findByFlightDetailsId(int flightId);
    void deleteByFlightDetailsId(int flightId);
}
