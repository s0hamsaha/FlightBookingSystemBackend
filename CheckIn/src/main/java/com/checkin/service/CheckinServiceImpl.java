package com.checkin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.checkin.entity.BookingDetails;
import com.checkin.entity.CheckinDetails;
import com.checkin.entity.FlightSeats;
import com.checkin.exception.CannotCheckinException;
import com.checkin.repository.CheckinRepository;

@Service
public class CheckinServiceImpl implements CheckinService {
	
	@Autowired
	private BookingClient bookingClient;
	
	@Autowired
	private FlightClient flightClient;
	
	@Autowired
	private CheckinRepository checkinRepository;
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 

	@Override
	public CheckinDetails createCheckIn(CheckinDetails checkinDetails, Long passengerId, Long bookingId) {
//		if(!bookingClient.checkBookingIdPresent(bookingId))
//			throw new CannotCheckinException("Please provide valid BookingId for Checkin");
		List<CheckinDetails> checkinList=getAllCheckInDetails();
		for(CheckinDetails checkin:checkinList)
		{
			if(checkin.getPassengerId()==passengerId)
				throw new CannotCheckinException("Passenger already checkedin ");
		}
		BookingDetails bookingDetails=bookingClient.getBookingDetailsById(bookingId);
		checkinDetails.setCheckInId(sequenceGenerator.generateSequence(CheckinDetails.SEQUENCE_NAME));
		checkinDetails.setBookingId(bookingDetails.getBookingId());
		checkinDetails.setPassengerId(passengerId);
		int startIndex=bookingDetails.getFlightDetails().getSeatIncrementer();
		List<FlightSeats> seatList=bookingDetails.getFlightDetails().getSeats();
		for(int i=startIndex;i<startIndex+1;i++)
		{
			String seat=seatList.get(i).getSeatNo();
			checkinDetails.setAllocatedSeat(seat);
		}
		bookingDetails.getFlightDetails().setSeatIncrementer(startIndex+1);
		System.out.println(bookingDetails.getFlightDetails());
		System.out.println(bookingDetails.getFlightId());
		flightClient.updateSeatIncrementor(bookingDetails.getFlightDetails(),bookingDetails.getFlightId());
		checkinDetails.setBookingDetails(bookingDetails);
		return checkinRepository.save(checkinDetails);
	}

	@Override
	public List<CheckinDetails> getAllCheckInDetails() {
		List<CheckinDetails> checkinDetailsList=checkinRepository.findAll();
		for(CheckinDetails checkinDetails:checkinDetailsList)
			checkinDetails.setBookingDetails(bookingClient.getBookingDetailsById(checkinDetails.getBookingId()));
		return checkinDetailsList;
	}

	@Override
	public CheckinDetails getCheckinDetailById(Long checkInId) {
		CheckinDetails checkinDetails=checkinRepository.findById(checkInId).orElseThrow(()->new CannotCheckinException("Not a valid checkin id"));
		checkinDetails.setBookingDetails(bookingClient.getBookingDetailsById(checkinDetails.getBookingId()));
		return checkinDetails;
	}
	@Override
	public CheckinDetails getCheckinDetailByPassengerId(Long passengerId) {
		CheckinDetails checkinDetails=checkinRepository.getByPassengerId(passengerId);
		checkinDetails.setBookingDetails(bookingClient.getBookingDetailsById(checkinDetails.getBookingId()));
		return checkinDetails;
	}

}
