package com.tayfint.meethub.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tayfint.meethub.dao.AccountDao;
import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Meeting;
import com.tayfint.meethub.model.Membership;
import com.tayfint.meethub.model.Transaction;
import com.tayfint.meethub.model.TransactionType;
import com.tayfint.meethub.model.TransactionStatus;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private TransactionService transactionService;

	static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Override
	public Account save(Account acct) {
		return accountDao.save(acct);
	}

	@Override
	public void freeze() {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Account deposit(Account toAccount, BigDecimal amount, String notes) {
		BigDecimal balance = toAccount.getBalance().add(amount);
		Long toAcctId = toAccount.getId();
		transactionService.save(new Transaction(balance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.DEPOSIT.toString(), "N/A", "I", "Cash Deposit", toAcctId, toAccount, notes));
		toAccount.setBalance(balance);
		accountDao.updateBalanceByAcctId(toAcctId, balance);
		return toAccount;
	}

	@Override
	public Account contribute(Account fromAccount, Account toAccount, BigDecimal amount, String name, String notes) {
		BigDecimal fromBalance = fromAccount.getBalance().subtract(amount);
		BigDecimal toBalance = toAccount.getBalance().add(amount);
		Long toAcctId = toAccount.getId();
		Long fromAcctId = fromAccount.getId();
		transactionService.save(new Transaction(fromBalance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.CONTRIBUTION.toString(), "N/A", "O", "Contribution", fromAcctId, fromAccount, notes));
		fromAccount.setBalance(fromBalance);
		accountDao.updateBalanceByAcctId(fromAcctId, fromBalance);
		transactionService.save(new Transaction(toBalance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.CONTRIBUTION.toString(), name, "I", "Contribution from " + name, fromAcctId, toAcctId,
				toAccount, notes));
		toAccount.setBalance(toBalance);
		accountDao.updateBalanceByAcctId(toAcctId, toBalance);
		return fromAccount;
	}

	@Override
	public Account addToSavings(Account fromAccount, Account toAccount, BigDecimal amount, String name, String notes) {
		BigDecimal fromBalance = fromAccount.getBalance().subtract(amount);
		BigDecimal toBalance = toAccount.getBalance().add(amount);
		Long toAcctId = toAccount.getId();
		Long fromAcctId = fromAccount.getId();
		transactionService.save(new Transaction(fromBalance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.SAVING.toString(), "N/A", "O", "Savings", fromAcctId, fromAccount, notes));
		fromAccount.setBalance(fromBalance);
		accountDao.updateBalanceByAcctId(fromAcctId, fromBalance);
		transactionService.save(new Transaction(toBalance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.SAVING.toString(), name, "I", "Savings from " + name, fromAcctId, toAcctId, toAccount, notes));
		toAccount.setBalance(toBalance);
		accountDao.updateBalanceByAcctId(toAcctId, toBalance);
		return fromAccount;
	}

	@Override
	public Account withdraw(Account fromAccount, BigDecimal amount, String notes) {
		BigDecimal balance = fromAccount.getBalance().subtract(amount);
		Long fromAcctId = fromAccount.getId();
		transactionService.save(new Transaction(balance, amount, TransactionStatus.PROCESSED.toString(),
				TransactionType.WITHDRAW.toString(), "N/A", "O", "Cash Withdrawal", fromAcctId, fromAccount, notes));
		fromAccount.setBalance(balance);
		accountDao.updateBalanceByAcctId(fromAcctId, balance);
		return fromAccount;
	}

	@Override
	public Account transfer(String transferFrom, String transferTo, BigDecimal amount, Account fromAccount,
			Account toAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSufficientFunds(Account acct, BigDecimal amount) {
		return acct.getBalance().subtract(amount).compareTo(new BigDecimal(0)) != 1;
	}

	@Override
	public List<Account> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getStatement(Account acct) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrow() {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Account> findById(Long id) {
		return accountDao.findById(id);
	}

	@Override
	public Account findByMembershipAndAcctType(Membership membership, String acctType) {
		return accountDao.findByMembershipAndAcctType(membership, acctType);
	}
	
	@Override
	public Account findByMeetingAndAcctType(Meeting mtg, String acctType) {
		return accountDao.findByMeetingAndAcctType(mtg, acctType);
	}

	@Override
	public List<Account> findByMembership(Membership membership) {
		return accountDao.findByMembership(membership);
	}
	
	@Override
	public List<Account> findByMeeting(Meeting mtg) {
		return accountDao.findByMeeting(mtg);
	}

}
