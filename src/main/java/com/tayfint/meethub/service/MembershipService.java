package com.tayfint.meethub.service;

import java.util.List;
import java.util.Optional;

import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.User;

public interface MembershipService {

	void add();

	void terminate();

	void suspend();

	void delete();

	void getAge();

	void getAccounts();

	void list();

	void show();

	void save(Membership membership);
	
	Optional<Membership> findMembershipById(Long membershipId);
	
	void deleteMembershipById(Long membershipId);
	
	List<Membership> findMembershipByUser(User user);
	
	Membership findMembershipByUserAndMeeting(User user, Meeting meeting);
	
	Membership fetchMembershipWithAccounts (Long membershipId);
}
