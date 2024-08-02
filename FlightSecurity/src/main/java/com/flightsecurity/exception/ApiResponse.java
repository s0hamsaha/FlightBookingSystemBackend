package com.flightsecurity.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
	private LocalDateTime dateTime;
	private List<String> errorMsg=new ArrayList<>();
	private int ErrorCode;
}
