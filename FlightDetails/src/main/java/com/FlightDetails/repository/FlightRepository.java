package com.FlightDetails.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.FlightDetails.entity.*;

@Repository
public interface FlightRepository extends JpaRepository<FlightDetails,Integer> {

}
