package com.search.exception;

@SuppressWarnings("serial")
public class NoFlightsAvailableException extends RuntimeException {
	public NoFlightsAvailableException(String msg) {
		super(msg);
	}

}
