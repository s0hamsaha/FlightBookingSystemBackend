package com.booking.entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetails {	
	private int id;
	private String flightNumber;
	private String source;
	private String destination;
	private Date date;
	private double fare;
	private int noOfSeats;
	private int seatIncrementer;
	@Transient
	private List<FlightSeats> seats=new ArrayList<>();
}
