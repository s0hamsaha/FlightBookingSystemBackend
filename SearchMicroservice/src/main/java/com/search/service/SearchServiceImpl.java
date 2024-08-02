package com.search.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.search.entity.FlightDetails;
import com.search.exception.NoFlightsAvailableException;
import com.search.repository.SearchRepository;
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchRepository searchRepository;
	
	@Override
	public List<FlightDetails> getFlightsBySourceDestinationDate(String source, String destination, Date date) {
		List<FlightDetails> flightDetailsList=new ArrayList<>();
		flightDetailsList=searchRepository.getFlightBySourceAndDestinationAndDate(source, destination, date);
		if(flightDetailsList.isEmpty())
			throw new NoFlightsAvailableException("No flights available for the given source and destination");
		return flightDetailsList;
	}


}
