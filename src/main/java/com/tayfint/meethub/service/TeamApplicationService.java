package com.tayfint.meethub.service;

import java.util.List;

import com.tayfint.meethub.model.Application;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.User;

public interface TeamApplicationService {

	Application save(Application application);
	
	Application findById(Long id);
	
	List<Application> findByUser(User user);
	
	List<Application> findByMeeting(Meeting mtg);
	
	void delete(Application application);
	
	void approve(Application application);
	
	void reject(Application application);
	
}
