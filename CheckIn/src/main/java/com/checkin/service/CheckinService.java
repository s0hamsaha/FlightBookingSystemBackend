package com.checkin.service;

import java.util.List;

import com.checkin.entity.CheckinDetails;

public interface CheckinService {
	CheckinDetails createCheckIn(CheckinDetails checkinDetails,Long passengerId,Long bookingId);
	List<CheckinDetails> getAllCheckInDetails();
	CheckinDetails getCheckinDetailById(Long checkInId);
	CheckinDetails getCheckinDetailByPassengerId(Long passengerId);
	
}
