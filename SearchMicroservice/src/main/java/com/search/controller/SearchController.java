package com.search.controller;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.search.entity.FlightDetails;
import com.search.service.SearchServiceImpl;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController {

	@Autowired
	private SearchServiceImpl searchServiceImpl;
	@GetMapping("/flights/getBySourceAndDestinationAndDate")
	public ResponseEntity<List<FlightDetails>> getFlightsBySourceDestinationDate(@RequestParam("source") String source,@RequestParam("destination") String destination,@RequestParam("date") Date date)
	{
		return ResponseEntity.ok(searchServiceImpl.getFlightsBySourceDestinationDate(source, destination, date));
	}
}
