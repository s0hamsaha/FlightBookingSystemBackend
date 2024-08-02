package com.FlightDetails.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.FlightDetails.entity.FlightDetails;
import com.FlightDetails.entity.FlightSeats;
import com.FlightDetails.exception.NoFlightFoundException;
import com.FlightDetails.repository.FlightRepository;
import com.FlightDetails.repository.FlightSeatRepository;

import jakarta.transaction.Transactional;

@Service
public class FlightDetailsServiceImpl implements FlightDetailsService {

	@Autowired
	private FlightRepository flightRepository;
	@Autowired
    private FlightSeatRepository flightSeatRepository;

	@Override
	public FlightDetails addDetails(FlightDetails flightDetails) {
		FlightDetails flight = new FlightDetails();
		flight.setFlightNumber(flightDetails.getFlightNumber());
		flight.setSource(flightDetails.getSource());
		flight.setDestination(flightDetails.getDestination());
		flight.setDate(flightDetails.getDate());
		flight.setFare(flightDetails.getFare());
		flight.setNoOfSeats(flightDetails.getNoOfSeats());
		flight.setSeatIncrementer(flightDetails.getSeatIncrementer());
		System.out.println(flight);
		flightRepository.save(flight);
		for (int i = 1; i <= flightDetails.getNoOfSeats(); i++) {
			String seatNo = String.valueOf(i);
			FlightSeats seat = new FlightSeats();
			seat.setFlightDetails(flight);
			if (i % 6 == 1)
				seatNo += "A";
			if (i % 6 == 2)
				seatNo += "B";
			if (i % 6 == 3)
				seatNo += "C";
			if (i % 6 == 4)
				seatNo += "D";
			if (i % 6 == 5)
				seatNo += "E";
			if (i % 6 == 0)
				seatNo += "F";
			seat.setSeatNo(seatNo);
			flight.getSeats().add(seat);
		}
		return flightRepository.save(flight);
	}

	@Override
	public List<FlightDetails> getAllDetails() {
		List<FlightDetails> flightDetailsList = new ArrayList<>();
		flightDetailsList = flightRepository.findAll();
		if (flightDetailsList.isEmpty())
			throw new NoFlightFoundException("No flights available");
		return flightDetailsList;
	}

	@Override
	public FlightDetails updatebyID(FlightDetails flightDetails, Integer id) {
		FlightDetails oldFlightDetail = flightRepository.findById(id)
				.orElseThrow(() -> new NoFlightFoundException("No flight with flightId " + id + " found"));

		if (flightDetails.getFlightNumber() != null)
			oldFlightDetail.setFlightNumber(flightDetails.getFlightNumber());

		if (flightDetails.getSource() != null)
			oldFlightDetail.setSource(flightDetails.getSource());

		if (flightDetails.getDestination() != null)
			oldFlightDetail.setDestination(flightDetails.getDestination());

		if (flightDetails.getDate() != null)
			oldFlightDetail.setDate(flightDetails.getDate());

		if (flightDetails.getFare() != 0)
			oldFlightDetail.setFare(flightDetails.getFare());

		if (flightDetails.getNoOfSeats() != 0)
			oldFlightDetail.setNoOfSeats(flightDetails.getNoOfSeats());

		if (flightDetails.getSeatIncrementer() != 0)
			oldFlightDetail.setSeatIncrementer(flightDetails.getSeatIncrementer());

		return flightRepository.save(oldFlightDetail);
	}

	@Override
	@Transactional
    public FlightDetails deleteByID(Integer id) {
        // Delete associated flight seats with the flightId
        flightSeatRepository.deleteByFlightDetailsId(id);
        
        // Delete flight details with the associated flightId
        FlightDetails flightDetails = flightRepository.findById(id).get();
        flightRepository.delete(flightDetails);

        return flightDetails;
    }

	@Override
	public FlightDetails getFlightByFlightId(Integer id) {
		FlightDetails flightDetails = flightRepository.findById(id)
				.orElseThrow(() -> new NoFlightFoundException("No flight with flightId " + id + " found"));
		return flightDetails;
	}
}
