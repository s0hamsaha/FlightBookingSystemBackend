package com.search.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.search.entity.FlightDetails;

@Repository
public interface SearchRepository extends JpaRepository<FlightDetails,Integer> {
	List<FlightDetails> getFlightBySourceAndDestinationAndDate(String source,String destination, Date date);
}
