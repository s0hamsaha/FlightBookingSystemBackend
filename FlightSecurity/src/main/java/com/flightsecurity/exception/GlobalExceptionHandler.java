package com.flightsecurity.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ApiResponse> handleNoFlightFoundException(NoUserFoundException exception) {
		ApiResponse res = new ApiResponse();
		res.setDateTime(LocalDateTime.now());
		res.getErrorMsg().add(exception.getMessage());
		System.out.println(res.getErrorMsg().get(0));
		res.setErrorCode(404);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        });
        ApiResponse res = new ApiResponse();
        res.setDateTime(LocalDateTime.now());
        res.setErrorMsg(errors);
        res.setErrorCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }
}
