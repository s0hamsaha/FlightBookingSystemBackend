package com.booking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "checkin-Details")
public class CheckinDetails {

	@Transient
    public static final String SEQUENCE_NAME = "checking_sequence";
	@Id
	private Long checkInId;
	private Long bookingId;
	private Long passengerId;
	private String allocatedSeat;
	
}
