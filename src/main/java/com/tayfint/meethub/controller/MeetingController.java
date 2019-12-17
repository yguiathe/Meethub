package com.tayfint.meethub.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.service.MeetingService;

@Controller
public class MeetingController {

	private final Logger logger = LoggerFactory.getLogger(MeetingController.class);

	@Autowired
	private MeetingService meetingService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "/meeting", method = RequestMethod.POST)
	public String createMeeting(@ModelAttribute("meeting") @Validated Meeting meeting, BindingResult result,
			@SessionAttribute("user") User user, Model model) {
		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			return "/home";
		} else if (meetingService.isNameAlreadyUsed(meeting.getName())) {
			model.addAttribute("nameUnavailable", true);
			return "/home";
		} else {
			meetingService.createMeetingAndAdminMembership(user, meeting);
			return "fragments/teams::team_created";
		}
	}

	@RequestMapping(value = "/meeting/find/{name}", method = RequestMethod.GET)
	public String findPublicMeetingByName(@PathVariable String name, @SessionAttribute("user") User user, Model model) {

		/*
		 * if (name == null || name.length() < 1) { model.addAttribute("name", name);
		 * return "fragments/teams::no_search_results"; } else {
		 */
		List<Meeting> mtgList = meetingService.findPublicMeetingByName(name.toUpperCase(), user.getId());
		if (mtgList.isEmpty()) {
			model.addAttribute("name", name);
			return "fragments/teams::no_search_results";
		} else {
			model.addAttribute("mtgList", mtgList);
			return "fragments/teams::search_results";
		}
	}
}
