package com.checkin.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value=CannotCheckinException.class)
	public ResponseEntity<ApiResponse>handleNoFlightFoundException(CannotCheckinException exception,WebRequest webRequest) 
	{
		ApiResponse res=new ApiResponse();
		res.setDateTime(LocalDateTime.now());
		res.setErrorMsg(exception.getMessage());
		res.setErrorCode(400);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.BAD_REQUEST);
	}
}
