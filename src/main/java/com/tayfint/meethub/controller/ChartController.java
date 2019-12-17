package com.tayfint.meethub.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tayfint.meethub.service.TransactionService;

@RestController
@RequestMapping("/api/charts")
public class ChartController {

	@Autowired
	TransactionService transactionService;
	
	@GetMapping(value="/last_qtr_io/{actId}", produces = "application/json")
	public List<Map<String, BigDecimal>> getIOBalanceByAccount(@PathVariable Long actId) {
		return transactionService.getLastQtrIOBalanceByAccount(actId);
	}
}
