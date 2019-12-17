package com.tayfint.meethub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.tayfint.meethub.model.Application;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.service.MeetingService;
import com.tayfint.meethub.service.TeamApplicationService;

@Controller
public class ApplicationController {

	private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	
	@Autowired
	MeetingService meetingService;
	
	@Autowired
	TeamApplicationService teamAppSvc;
	
	@RequestMapping(value = "/application/{mtgId}", method = RequestMethod.POST)
	public String saveApplication(@PathVariable Long mtgId, @SessionAttribute("user") User user, Model model) {
		logger.debug("Meeting ID" + mtgId);
		Application application = teamAppSvc.save(new Application(meetingService.findByMeetingId(mtgId).get(), user));
		
		//Auto-approve - Temp code to be moved into a separate method within this controller
		teamAppSvc.approve(application);
		model.addAttribute("application", application);
		return "fragments/teams::application_submitted";
	}
}
