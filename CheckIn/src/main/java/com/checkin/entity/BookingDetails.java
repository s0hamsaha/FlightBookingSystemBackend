package com.checkin.entity;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class BookingDetails {

	private Long bookingId;
	private int flightId;
	private String bookedByName;
	private long phoneNumber;
	private String email;
	private int passengerCount;
	private FlightDetails flightDetails;
	/*
	 * @Transient private double totalFare;
	 */

}

