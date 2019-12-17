package com.tayfint.meethub.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.model.dto.UserDto;
import com.tayfint.meethub.service.AccountService;
import com.tayfint.meethub.service.MeetingService;
import com.tayfint.meethub.service.MembershipService;

@Controller
@SessionAttributes({ "membership", "meeting", "viewType" })
@RequestMapping("/memberships")
public class MembershipController {

	static final Logger logger = LoggerFactory.getLogger(MembershipController.class);

	@Autowired
	private MembershipService membershipService;
	
	@Autowired
	private MeetingService meetingService;

	@Autowired
	private AccountService acctService;

	@Autowired
	private ModelMapper mm;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getAllMembershipsByUser(@SessionAttribute("user") User user, Model model) {

		Meeting mtg = new Meeting();

		// set default value
		mtg.setName("Simo Guiadem");
		mtg.setShortDesc("This is a short description of this meeting!");
		mtg.setCountryOfIncorp("Cameroon");
		mtg.setCity("Yaounde");
		mtg.setStreetAddr("Ngousso");
		mtg.setIsPublic(true);
		model.addAttribute("meeting", mtg);
		model.addAttribute("memberships", membershipService.findMembershipByUser(user));

		return "fragments/landing::teams";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String postMembershipDetailsById(@RequestParam Long id, @RequestParam String type, @SessionAttribute("user") User user,
			Model model) {
		logger.debug("Membership: " + id);
		BigDecimal [] balances = new BigDecimal[4];
		Membership membership = new Membership();
		Meeting meeting = new Meeting();
		if(type.equalsIgnoreCase("N")) {
			membership = membershipService.findMembershipById(id).get();
			balances = getAcctBalances(acctService.findByMembership(membership));
		} else {
			meeting = meetingService.findByMeetingId(id).get();
			balances = getAcctBalances(acctService.findByMeeting(meeting));
		}
		model.addAttribute("meeting", meeting);
		model.addAttribute("membership", membership);
		model.addAttribute("userDto", mm.map(user, UserDto.class));
		model.addAttribute("viewType", type);
		model.addAttribute("checkingBal", balances[0]);
		model.addAttribute("savingBal", balances[1]);
		model.addAttribute("loanBal", balances[2]);
		model.addAttribute("investmentBal", balances[3]);
		return "User/meeting";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String getMembershipDetailsById(@RequestParam Long id, @RequestParam String type, @SessionAttribute("user") User user,
			Model model) {
		return postMembershipDetailsById(id, type, user, model);
	}
	
	public BigDecimal[] getAcctBalances(List<Account> acctList) {
		BigDecimal [] balances = new BigDecimal[4];
		Arrays.fill(balances, BigDecimal.ZERO);
		for (Account account : acctList) {
			switch (account.getAcctType()) {
			case "CHK":
				balances[0] = balances[0].add(account.getBalance());
				break;
			case "SAV":
				balances[1] = balances[1].add(account.getBalance());
				break;
			case "LOA":
				balances[2] = balances[2].add(account.getBalance());
				break;
			case "INV":
				balances[3] = balances[3].add(account.getBalance());
				break;
			}
		}
		return balances;
	}
}
