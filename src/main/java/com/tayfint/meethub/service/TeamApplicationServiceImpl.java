package com.tayfint.meethub.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tayfint.meethub.dao.ApplicationDao;
import com.tayfint.meethub.model.Application;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.User;

@Service("teamApplicationService")
@Transactional
public class TeamApplicationServiceImpl implements TeamApplicationService {

	@Autowired
	ApplicationDao applicationDao;
	
	@Autowired
	MeetingService meetingService;
	
	@Autowired
	UserService userService;

	@Override
	public Application save(Application application) {
		userService.IncrementAppCnt(application.getUser().getId());
		return applicationDao.save(application);
	}

	@Override
	public List<Application> findByUser(User user) {
		return applicationDao.findByUser(user);
	}

	@Override
	public List<Application> findByMeeting(Meeting mtg) {
		return applicationDao.findByMeeting(mtg);
	}

	@Override
	public void delete(Application application) {
		applicationDao.delete(application);
	}

	@Override
	public void approve(Application application) {
		Application app = findById(application.getId());
		app.setStatusCd("1");
		userService.decrementAppCnt(application.getUser().getId());
		meetingService.joinMeeting(application.getUser(), application.getMeeting());
	}

	@Override
	public void reject(Application application) {
		Application app = findById(application.getId());
		app.setStatusCd("2");
		userService.decrementAppCnt(application.getUser().getId());
	}

	@Override
	public Application findById(Long id) {
		return applicationDao.findById(id).get();
	}
}
