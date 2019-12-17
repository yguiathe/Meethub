package com.tayfint.meethub.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.tayfint.meethub.model.Application;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.User;

public interface ApplicationDao extends CrudRepository<Application, Long> {
	Optional<Application> findById(Long appId);
	List<Application> findByUser(User user);
	List<Application> findByMeeting(Meeting mtg);
}
