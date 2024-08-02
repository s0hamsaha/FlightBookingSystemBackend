package com.checkin.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
