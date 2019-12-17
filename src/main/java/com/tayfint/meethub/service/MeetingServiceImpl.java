package com.tayfint.meethub.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tayfint.meethub.dao.MeetingDao;
import com.tayfint.meethub.model.CheckingAccount;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.SavingsAccount;
import com.tayfint.meethub.model.User;

@Service("meetingService")
@Transactional
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	MeetingDao meetingDao;
	
	@Autowired
	MembershipService membershipService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void createMeetingAndAdminMembership(User user, Meeting meeting) {
		Membership membership = new Membership();
		membership.setTypeCd("1");
		userService.IncrementTeamsCnt(user.getId());
		meeting.setMembersCnt(meeting.getMembersCnt() + 1);
		membership.setMeeting(saveMeeting(meeting));
		membership.setUser(user);
		membershipService.save(membership);
	}
	
	@Override
	public void joinMeeting(User user, Meeting meeting) {
		Membership membership = new Membership();
		userService.IncrementTeamsCnt(user.getId());
		meeting.setMembersCnt(meeting.getMembersCnt() + 1);
		membership.setMeeting(meeting);
		membership.setUser(user);
		membershipService.save(membership);
	}

	@Override
	public Meeting saveMeeting(Meeting meeting) {
		meeting.addAccount(new CheckingAccount());
		meeting.addAccount(new SavingsAccount());
		return meetingDao.save(meeting);
	}

	@Override
	public Optional<Meeting> findByMeetingId(Long meetingId) {
		return meetingDao.findById(meetingId);
	}

	@Override
	public Meeting findByMeetingName(String name) {
		return meetingDao.findByName(name);
	}

	@Override
	public List<Meeting> findAllMeetings() {
		return meetingDao.findAll();
	}

	@Override
	public void deleteMeetingById(Long meetingId) {
		meetingDao.deleteById(meetingId);		
	}

	@Override
	public boolean isNameAlreadyUsed(String name) {
		if (null != meetingDao.findByName(name)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Meeting> findPublicMeetingByName(String name, Long userId) {
		return meetingDao.findPublicMeetingByName(name, userId);
	}
	
	public void IncrementMembersCnt(Long mtgId) {
		Meeting mtg = meetingDao.findById(mtgId).get();	
		mtg.setMembersCnt(mtg.getMembersCnt() + 1);
	}
	
	public void decrementMembersCnt(Long mtgId) {
		Meeting mtg = meetingDao.findById(mtgId).get();	
		mtg.setMembersCnt(mtg.getMembersCnt() - 1);
	}
	
}
