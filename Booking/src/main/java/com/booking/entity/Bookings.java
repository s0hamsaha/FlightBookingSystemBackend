package com.booking.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Document("bookings")
public class Bookings {

    @Transient
    public static final String SEQUENCE_NAME = "booking_sequence";
	@Id
	private Long bookingId;
	private int flightId;
	private String bookedByName;
	private long phoneNumber;
	private String email;
	private int passengerCount;
	@Transient
	private List<Passengers> passengers=new ArrayList<>();
	@Transient
	private FlightDetails flightDetails;
	@Transient 
	private double totalFare;

}
