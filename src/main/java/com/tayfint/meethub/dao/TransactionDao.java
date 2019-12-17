package com.tayfint.meethub.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tayfint.meethub.model.Account;
import com.tayfint.meethub.model.Transaction;

public interface TransactionDao extends CrudRepository<Transaction, Long> {

	Page<Transaction> findByAccount(Account acct, Pageable pageRequest);

	Page<Transaction> findByAccountAndOriginatorName(Account acct, String originatorName, Pageable pageRequest);

	void deleteById(Long id);

	@Query("SELECT EXTRACT(MONTH FROM t.created) AS month, SUM(CASE WHEN t.direction = 'O' THEN t.amount ELSE 0 END) AS obal, "
			+ "SUM(CASE WHEN t.direction = 'I' THEN t.amount ELSE 0 END) AS ibal "
			+ "FROM Transaction t "
			+ "WHERE t.account.id = :actId AND t.created >= :fromDate"
			+ " GROUP BY EXTRACT(MONTH FROM t.created)")
	List<Map<String, BigDecimal>> getLastQtrIOBalanceByAccount(@Param("actId") Long actId, @Param("fromDate")LocalDateTime fromDate);

}
