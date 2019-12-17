package com.tayfint.meethub.model.dto;

import java.math.BigDecimal;

import com.tayfint.meethub.model.Account;

public class TransactionDto {
	
	private Account originatingAct;
	private Account mtgCheckingAct;
	private Account mtgSavingAct;
	private BigDecimal amount;
	private String transType;
	private String notes;
	private String mtgName;
	
	public Account getOriginatingAct() {
		return originatingAct;
	}
	public void setOriginatingAct(Account originatingAct) {
		this.originatingAct = originatingAct;
	}
	public Account getMtgCheckingAct() {
		return mtgCheckingAct;
	}
	public void setMtgCheckingAct(Account mtgCheckingAct) {
		this.mtgCheckingAct = mtgCheckingAct;
	}
	public Account getMtgSavingAct() {
		return mtgSavingAct;
	}
	public void setMtgSavingAct(Account mtgSavingAct) {
		this.mtgSavingAct = mtgSavingAct;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	public TransactionDto(Account originatingAct, BigDecimal amount, String transType, String notes) {
		super();
		this.originatingAct = originatingAct;
		this.amount = amount;
		this.transType = transType;
		this.notes = notes;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getMtgName() {
		return mtgName;
	}
	public void setMtgName(String mtgName) {
		this.mtgName = mtgName;
	}
	
}
