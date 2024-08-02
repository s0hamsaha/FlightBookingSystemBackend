package com.booking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("passengers")
public class Passengers {
    @Transient
    public static final String SEQUENCE_NAME = "passenger_sequence";
	@Id
	private Long passengerId;
	private int flightId;
	private Long bookingId;
	private String passengerName;
	private long passengerPhoneNumber;
	private String passengerEmail;
	@Transient
	private String checkin;
}
