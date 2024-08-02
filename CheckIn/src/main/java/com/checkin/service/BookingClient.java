package com.checkin.service;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.checkin.entity.BookingDetails;


@FeignClient(name="BOOKING")
public interface BookingClient {

	@GetMapping("/booking/getByBookingId/{bookingId}")
	BookingDetails getBookingDetailsById(@PathVariable Long bookingId);
	
	@GetMapping("/booking/checkBookingIdAvailable/{bookingId}")
	boolean checkBookingIdPresent(@PathVariable Long bookingId);
}
