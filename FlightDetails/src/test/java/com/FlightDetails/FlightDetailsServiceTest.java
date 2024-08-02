package com.FlightDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.FlightDetails.entity.FlightDetails;
import com.FlightDetails.entity.FlightSeats;
import com.FlightDetails.exception.NoFlightFoundException;
import com.FlightDetails.repository.FlightRepository;
import com.FlightDetails.repository.FlightSeatRepository;
import com.FlightDetails.service.FlightDetailsServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FlightDetailsServiceTest {

	@Mock
	private FlightRepository flightRepository;
	
	@Mock
	private FlightSeatRepository flightSeatRepository;

	@InjectMocks
	private FlightDetailsServiceImpl flightDetailsService;

	@Test
	public void testAddDetails() {
		// Prepare mock data
		FlightDetails flightDetails = new FlightDetails();
		flightDetails.setFlightNumber("BF105");
		flightDetails.setSource("Kolkata");
		flightDetails.setDestination("Mumbai");
		flightDetails.setDate(new Date(System.currentTimeMillis()));
		flightDetails.setFare(5000.0);
		flightDetails.setNoOfSeats(50);
		flightDetails.setSeatIncrementer(0);

		// Mock behavior of repository
		when(flightRepository.save(any(FlightDetails.class))).thenReturn(flightDetails);

		// Call the service method
		flightDetailsService.addDetails(flightDetails);

		for (int i = 1; i <= flightDetails.getNoOfSeats(); i++) {
			String seatNo = String.valueOf(i);
			FlightSeats seat = new FlightSeats();
			seat.setFlightDetails(flightDetails);
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
			flightDetails.getSeats().add(seat);
		}
		FlightDetails result = flightRepository.save(flightDetails);

		// Verify the result
		assertEquals(flightDetails, result);
		assertEquals(50, result.getSeats().size());
	}

	@Test
	public void testGetAllDetails() {
		// Prepare mock data
		List<FlightDetails> flightDetailsList = new ArrayList<>();
		flightDetailsList.add(new FlightDetails());

		// Mock behavior of repository
		when(flightRepository.findAll()).thenReturn(flightDetailsList);

		// Call the service method
		List<FlightDetails> result = flightDetailsService.getAllDetails();

		// Verify the result
		assertEquals(flightDetailsList.size(), result.size());
	}

	@Test
	public void testUpdateByID() {
		// Prepare mock data
		FlightDetails flightDetails = new FlightDetails();
		flightDetails.setId(1);
		flightDetails.setFlightNumber("BF105");
		flightDetails.setSource("Kolkata");
		flightDetails.setDestination("Mumbai");
		flightDetails.setDate(new Date(System.currentTimeMillis()));
		flightDetails.setFare(5000.0);
		flightDetails.setNoOfSeats(50);
		flightDetails.setSeatIncrementer(0);
		// Mock behavior of repository
		when(flightRepository.findById(1)).thenReturn(Optional.of(flightDetails));
		when(flightRepository.save(any(FlightDetails.class))).thenReturn(flightDetails);

		// Call the service method
		FlightDetails updatedFlightDetails = flightDetailsService.updatebyID(flightDetails, 1);

		// Verify the result
		assertEquals(flightDetails, updatedFlightDetails);
	}

	@Test
	public void testDeleteByID() {
		// Prepare mock data
		 FlightDetails flightDetails = new FlightDetails();
	        flightDetails.setId(1);
	        flightDetails.setFlightNumber("BF105");
	        flightDetails.setSource("Kolkata");
	        flightDetails.setDestination("Mumbai");
	        flightDetails.setDate(new Date(System.currentTimeMillis()));
	        flightDetails.setFare(5000.0);
	        flightDetails.setNoOfSeats(50);
	        flightDetails.setSeatIncrementer(0);

	        // Mock behavior of repository
	        when(flightRepository.findById(1)).thenReturn(Optional.of(flightDetails));

	        // Call the service method
	        FlightDetails deletedFlightDetails = flightDetailsService.deleteByID(1);

	        // Verify the result
	        assertEquals(flightDetails, deletedFlightDetails);
	        verify(flightSeatRepository).deleteByFlightDetailsId(1);
	        verify(flightRepository).delete(flightDetails);
	}

	@Test
	public void testGetFlightByFlightId() {
		// Prepare mock data
		FlightDetails flightDetails = new FlightDetails();
		flightDetails.setId(1);
		flightDetails.setFlightNumber("BF105");
		flightDetails.setSource("Kolkata");
		flightDetails.setDestination("Mumbai");
		flightDetails.setDate(new Date(System.currentTimeMillis()));
		flightDetails.setFare(5000.0);
		flightDetails.setNoOfSeats(50);
		flightDetails.setSeatIncrementer(0);

		// Mock behavior of repository
		when(flightRepository.findById(1)).thenReturn(Optional.of(flightDetails));

		// Call the service method
		FlightDetails retrievedFlightDetails = flightDetailsService.getFlightByFlightId(1);

		// Verify the result
		assertEquals(flightDetails, retrievedFlightDetails);
	}

	@Test
	public void testGetFlightByFlightId_NotFound() {
		// Mock behavior of repository
		when(flightRepository.findById(1)).thenReturn(Optional.empty());

		// Call the service method and verify exception
		assertThrows(NoFlightFoundException.class, () -> flightDetailsService.getFlightByFlightId(1));
	}
}
