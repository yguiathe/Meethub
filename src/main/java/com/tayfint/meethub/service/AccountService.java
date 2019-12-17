package com.tayfint.meethub.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;

public interface AccountService {

	Account save(Account acct);
	
	Account findByMembershipAndAcctType(Membership membership, String acctType);
	
	List<Account> findByMembership(Membership membership);

	void freeze();

	void delete();
    
    Account transfer(String transferFrom, String transferTo, BigDecimal amount, Account fromAccount, Account toAccount);

	List<Account> list();

	void show();

	void getStatement(Account acct);

	void borrow();

	Optional<Account> findById(Long id);

	boolean hasSufficientFunds(Account acct, BigDecimal amount);

	Account findByMeetingAndAcctType(Meeting mtg, String acctType);

	Account addToSavings(Account fromAccount, Account toAccount, BigDecimal amount, String name, String notes);

	Account withdraw(Account fromAccount, BigDecimal amount, String notes);

	Account contribute(Account fromAccount, Account toAccount, BigDecimal amount, String name, String notes);

	Account deposit(Account toAccount, BigDecimal amount, String notes);

	List<Account> findByMeeting(Meeting mtg);
}
