package com.booking.exception;

@SuppressWarnings("serial")
public class NoBookingFoundException extends RuntimeException {
	public NoBookingFoundException(String msg)
	{
		super(msg);
	}

}
