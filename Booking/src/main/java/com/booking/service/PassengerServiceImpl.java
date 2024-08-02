package com.booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entity.CheckinDetails;
import com.booking.entity.Passengers;
import com.booking.repository.CheckinRepository;
import com.booking.repository.PassengerRepository;

@Service
public class PassengerServiceImpl implements PassengerService {
	
	@Autowired
	private PassengerRepository passengerRepository; 
	
	@Autowired
	private CheckinRepository checkinRepository; 
	
	@Autowired
	private SequenceGenerator sequenceGenerator; 
	
	@Override
	public Passengers addPassenger(Passengers passengers) {
		passengers.setPassengerId(sequenceGenerator.generateSequence(Passengers.SEQUENCE_NAME));
		return passengerRepository.save(passengers);
	}

	@Override
	public List<Passengers> getAllPassengers() {
		List<Passengers> passengerList=passengerRepository.findAll();
		for(Passengers passenger:passengerList)
		{
			if(checkinRepository.getByPassengerId(passenger.getPassengerId())!=null)
			{
				CheckinDetails checkinDetails=checkinRepository.getByPassengerId(passenger.getPassengerId());
				passenger.setCheckin(checkinDetails.getCheckInId().toString());
			}
			else {
				passenger.setCheckin("Not checked-in");
			}
		}
		return passengerList;
	}

	@Override
	public Passengers getByPassengerId(Long passengerId) {
		return passengerRepository.findById(passengerId).get();
	}

	@Override
	public List<Passengers> getByBookingId(Long bookingId) {
		List<Passengers> passengerList=passengerRepository.findAll();
		List<Passengers> newPassengerList=new ArrayList<>();
		for(Passengers passenger:passengerList)
			if(passenger.getBookingId().equals(bookingId))
				newPassengerList.add(passenger);
		return newPassengerList;
	}
}
