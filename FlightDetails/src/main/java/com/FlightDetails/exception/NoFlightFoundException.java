package com.FlightDetails.exception;

@SuppressWarnings("serial")
public class NoFlightFoundException extends RuntimeException {
	public NoFlightFoundException(String msg) {
		super(msg);
	}

}
