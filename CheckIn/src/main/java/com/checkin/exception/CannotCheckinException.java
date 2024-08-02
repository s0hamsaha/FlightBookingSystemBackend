package com.checkin.exception;

@SuppressWarnings("serial")
public class CannotCheckinException extends RuntimeException {
	public CannotCheckinException(String msg)
	{
		super(msg);
	}

}
