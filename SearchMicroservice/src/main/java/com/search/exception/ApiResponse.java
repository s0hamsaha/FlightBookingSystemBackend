package com.search.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	private LocalDateTime dateTime;
	private String errorMsg;
	private int setErrorCode;
}
