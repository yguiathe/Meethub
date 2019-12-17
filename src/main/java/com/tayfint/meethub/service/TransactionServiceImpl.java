package com.tayfint.meethub.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tayfint.meethub.dao.TransactionDao;
import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Transaction;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;

	static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public Transaction save(Transaction transaction) {
		return transactionDao.save(transaction);
	}

	@Override
	public Page<Transaction> findByAccount(Account acct, int page) {
		Page<Transaction> transactionList = transactionDao.findByAccount(acct,
				PageRequest.of(page, 20, Sort.Direction.DESC, "created"));
		logger.debug("****************** Number of Transactions is: " + transactionList.getTotalPages());
		return transactionList;
	}

	@Override
	public Page<Transaction> findByAccountAndOriginatorName(Account acct, String originatorName, int page) {
		Page<Transaction> transactionList = transactionDao.findByAccountAndOriginatorName(acct, originatorName,
				PageRequest.of(page, 20, Sort.Direction.DESC, "created"));
		logger.debug("****************** Number of Transactions is: " + transactionList.getTotalPages());
		return transactionList;
	}

	@Override
	public void deleteById(Long id) {
		transactionDao.deleteById(id);
	}

	@Override
	public List<Map<String, BigDecimal>> getLastQtrIOBalanceByAccount(Long actId) {
		return transactionDao.getLastQtrIOBalanceByAccount(actId, LocalDateTime.now().minus(2, ChronoUnit.MONTHS));
	}

}
