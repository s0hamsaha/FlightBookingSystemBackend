package com.FlightDetails.service;
import java.util.List;
import com.FlightDetails.entity.FlightDetails;

public interface FlightDetailsService {
	FlightDetails addDetails(FlightDetails flightDetails);

	List<FlightDetails> getAllDetails();

	FlightDetails updatebyID(FlightDetails flightDetails, Integer id);
	
	FlightDetails getFlightByFlightId(Integer id);

	FlightDetails deleteByID(Integer id);

}
