package com.booking.service;

import java.util.List;

import com.booking.entity.Bookings;

public interface BookingService {
	Bookings addBooking(Bookings bookings,int flightId);
	List<Bookings> getAllBookings();
	Bookings getByBookingId(Long BookingId);
	boolean isBookingIdAvailable(Long BookingId);
	List<Bookings> getBookingsByEmail(String email);
}
