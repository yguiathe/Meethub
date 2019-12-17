package com.tayfint.meethub.service;

public interface LoanService {

	void add();

	void getAmortizationTbl();

	void getInterestRate();

	void getMonthlyPayment();

	void getOutstandingBalance();

	void payoff();

	void getAge();

	void getExpectedPayOffDate();

	void getStatus();

	void getLoanAmount();

	void getTotalInterestPaid();

	void list();

	void getLastPaymentDate();
}
