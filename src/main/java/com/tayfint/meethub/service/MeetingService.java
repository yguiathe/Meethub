package com.tayfint.meethub.service;

import java.util.List;
import java.util.Optional;

import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.User;

public interface MeetingService {
	
	Meeting saveMeeting(Meeting meeting);
	
	void createMeetingAndAdminMembership(User user, Meeting meeting);
	
	void joinMeeting(User user, Meeting meeting);
	
	Optional<Meeting> findByMeetingId(Long meetingId);
	
	Meeting findByMeetingName(String name);
	
	List<Meeting> findPublicMeetingByName(String name, Long long1);
	
	void deleteMeetingById(Long meetingId);
	
	List<Meeting> findAllMeetings();

	boolean isNameAlreadyUsed(String name);
}
