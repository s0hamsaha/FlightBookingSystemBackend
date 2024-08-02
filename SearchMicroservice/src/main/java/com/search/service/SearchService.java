package com.search.service;

import java.sql.Date;
import java.util.List;

import com.search.entity.FlightDetails;

public interface SearchService {
	List<FlightDetails> getFlightsBySourceDestinationDate(String source,String destination,Date date);

}
