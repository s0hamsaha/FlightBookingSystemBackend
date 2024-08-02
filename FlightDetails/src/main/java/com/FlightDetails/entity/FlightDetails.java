package com.FlightDetails.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetails {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String flightNumber;
	@Size(min=3,message = "Source should be minimum of length 3")
	private String source;
	@Size(min=3,message = "Destination should be minimum of length 3")
	private String destination;
	private Date date;
	@Min(value=1,message = "Fare shouldn't be zero")
	private double fare;
	@Min(value=1,message = "Seats shouldn't be zero")
	private int noOfSeats;
	private int seatIncrementer;
	@OneToMany(mappedBy = "flightDetails",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<FlightSeats> seats=new ArrayList<>();
}

