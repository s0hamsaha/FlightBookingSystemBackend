package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entity.Bookings;
import com.booking.entity.FlightDetails;
import com.booking.entity.Passengers;
import com.booking.exception.NoBookingFoundException;
import com.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingRepository bookingRepository; 
	
	@Autowired
	private PassengerServiceImpl passengerServiceImpl;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Autowired
	private FlightClient flightClient;
	
	@Override
	public Bookings addBooking(Bookings bookings,int flightId) {
		bookings.setBookingId(sequenceGenerator.generateSequence(Bookings.SEQUENCE_NAME));
		FlightDetails flightDetails=flightClient.getFlightOfBooking(flightId);
		bookings.setFlightId(flightId);
		int availableSeats=flightDetails.getNoOfSeats();
		if(bookings.getPassengerCount()>availableSeats)
			throw new NoBookingFoundException("No seats available. Try another flight");
		availableSeats-=bookings.getPassengerCount();
		flightDetails.setNoOfSeats(availableSeats);
		flightClient.updateSeats(flightDetails, flightId);
		bookings.setTotalFare((flightDetails.getFare()+(flightDetails.getFare()*0.05))*bookings.getPassengerCount());
		Bookings b=bookingRepository.save(bookings);
		List<Passengers> newPassengersList = new ArrayList<>();
		for(Passengers passenger:bookings.getPassengers())
		{
			Passengers p=new Passengers();
			p.setBookingId(b.getBookingId());
			p.setFlightId(b.getFlightId());
			p.setPassengerName(passenger.getPassengerName());
			p.setPassengerPhoneNumber(passenger.getPassengerPhoneNumber());
			p.setPassengerEmail(passenger.getPassengerEmail());
			passengerServiceImpl.addPassenger(p);
			newPassengersList.add(p);
		}
		bookings.setPassengers(newPassengersList);
		flightDetails=flightClient.getFlightOfBooking(flightId);
		bookings.setFlightDetails(flightDetails);
		return bookings;
	}

	@Override
	public List<Bookings> getAllBookings() {
		List<Bookings> bookingList=bookingRepository.findAll();
		if(bookingList.isEmpty())
			throw new NoBookingFoundException("No Bookings done till yet");
		for(Bookings Booking:bookingList)
		{
			Booking.setPassengers(passengerServiceImpl.getByBookingId(Booking.getBookingId()));
			Booking.setFlightDetails(flightClient.getFlightOfBooking(Booking.getFlightId()));
			Booking.setTotalFare((Booking.getFlightDetails().getFare()+(Booking.getFlightDetails().getFare()*0.05))*Booking.getPassengerCount());
		}
		return bookingList;
	}

	@Override
	public Bookings getByBookingId(Long BookingId) {
		Bookings bookings=bookingRepository.findById(BookingId).orElseThrow(()-> new NoBookingFoundException("No Booking with BookingId "+BookingId+" found"));
		bookings.setPassengers(passengerServiceImpl.getByBookingId(BookingId));
		bookings.setFlightDetails(flightClient.getFlightOfBooking(bookings.getFlightId()));
		bookings.setTotalFare((bookings.getFlightDetails().getFare()+(bookings.getFlightDetails().getFare()*0.05))*bookings.getPassengerCount());
		return bookings;
	}

	@Override
	public boolean isBookingIdAvailable(Long BookingId) {
		Optional<Bookings> booking=bookingRepository.findById(BookingId);
		if(booking.isEmpty())
			return false;
		else
			return true;
	}

	@Override
	public List<Bookings> getBookingsByEmail(String email) {
		List<Bookings> bookingList=bookingRepository.getByEmail(email);
		for(Bookings booking:bookingList)
		{
			booking.setPassengers(passengerServiceImpl.getByBookingId(booking.getBookingId()));
			booking.setFlightDetails(flightClient.getFlightOfBooking(booking.getFlightId()));
			booking.setTotalFare((booking.getFlightDetails().getFare()+(booking.getFlightDetails().getFare()*0.05))*booking.getPassengerCount());
		}
		return bookingList;
	}

}
