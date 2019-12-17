package com.tayfint.meethub.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.User;
import com.tayfint.meethub.model.dto.TransactionDto;
import com.tayfint.meethub.service.AccountService;
import com.tayfint.meethub.service.TransactionService;

@Controller
@RequestMapping("/Accounts")
public class AccountController {

	static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transService;

	@RequestMapping(value = "/{actType}", method = RequestMethod.GET)
	public String getAccountDetails(@PathVariable("actType") String actType,
			@SessionAttribute("membership") Membership membership, @SessionAttribute("viewType") String viewType,
			@SessionAttribute("meeting") Meeting mtg, Model model) {
		logger.debug("Account Type: " + actType);
		Account act = null;
		if (viewType.equalsIgnoreCase("N")) {
			act = accountService.findByMembershipAndAcctType(membership, actType);
		} else {
			act = accountService.findByMeetingAndAcctType(mtg, actType);
		}
		if (act != null) {
			model.addAttribute("account", act);
			model.addAttribute("transactions", transService.findByAccount(act, 0));
			return "fragments/account::account_details";
		} else {
			model.addAttribute("actType", actType);
			return "fragments/account::no_account_details";
		}
	}

	@RequestMapping(value = "/Transact/{actId}/{transType}", method = RequestMethod.GET)
	public String getTransactionForm(@PathVariable("actId") Long actId, @PathVariable("transType") String transType,
			@SessionAttribute("membership") Membership membership, @SessionAttribute("meeting") Meeting meeting,
			@SessionAttribute("viewType") String viewType, Model model) {
		TransactionDto transDto = new TransactionDto(accountService.findById(actId).get(), new BigDecimal("0"),
				transType, "");
		if (viewType.equalsIgnoreCase("N")) {
			if (transType.equalsIgnoreCase("con")) {
				transDto.setMtgCheckingAct(accountService.findByMeetingAndAcctType(membership.getMeeting(), "CHK"));
			} else if (transType.equalsIgnoreCase("sav")) {
				transDto.setMtgSavingAct(accountService.findByMeetingAndAcctType(membership.getMeeting(), "SAV"));
			}
			transDto.setMtgName(membership.getMeeting().getName());
		} else {
			if (transType.equalsIgnoreCase("con")) {
				transDto.setMtgCheckingAct(accountService.findByMeetingAndAcctType(meeting, "CHK"));
			} else if (transType.equalsIgnoreCase("sav")) {
				transDto.setMtgSavingAct(accountService.findByMeetingAndAcctType(meeting, "SAV"));
			}
			transDto.setMtgName(meeting.getName());
		}
		model.addAttribute("transDto", transDto);
		return "fragments/account::transaction_modal";
	}

	@RequestMapping(value = "/contribute", method = RequestMethod.POST)
	public String contribute(@ModelAttribute("transDto") @Validated TransactionDto transDto, BindingResult result,
			@SessionAttribute("user") User user, Model model) {
		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			return "/home";
		} else {
			accountService.contribute(transDto.getOriginatingAct(), transDto.getMtgCheckingAct(), transDto.getAmount(),
					user.getFnLN(), transDto.getNotes());
		}
		return "fragments/account::successful-transaction";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saving(@ModelAttribute("transDto") @Validated TransactionDto transDto, BindingResult result,
			@SessionAttribute("user") User user, Model model) {
		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			return "/home";
		} else {
			accountService.addToSavings(transDto.getOriginatingAct(), transDto.getMtgSavingAct(), transDto.getAmount(),
					user.getFnLN(), transDto.getNotes());
		}
		return "fragments/account::successful-transaction";
	}

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String deposit(@ModelAttribute("transDto") @Validated TransactionDto transDto, BindingResult result,
			@SessionAttribute("user") User user, Model model) {
		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			return "/home";
		} else {
			accountService.deposit(transDto.getOriginatingAct(), transDto.getAmount(), transDto.getNotes());
		}
		return "fragments/account::successful-transaction";
	}

	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdraw(@ModelAttribute("transDto") @Validated TransactionDto transDto, BindingResult result,
			@SessionAttribute("user") User user, Model model) {
		if (result.hasErrors()) {
			logger.info("Binding Errors : {}", result.getAllErrors().get(0));
			return "/home";
		} else {
			accountService.withdraw(transDto.getOriginatingAct(), transDto.getAmount(), transDto.getNotes());
		}
		return "fragments/account::successful-transaction";
	}
}
