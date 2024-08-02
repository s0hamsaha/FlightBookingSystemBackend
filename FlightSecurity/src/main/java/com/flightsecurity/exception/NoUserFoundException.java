package com.flightsecurity.exception;

@SuppressWarnings("serial")
public class NoUserFoundException extends RuntimeException {
	public NoUserFoundException(String msg) {
		super(msg);
	}

}
