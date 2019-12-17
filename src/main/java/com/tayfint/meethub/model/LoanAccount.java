package com.tayfint.meethub.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("LOA")
public class LoanAccount extends Account {

	private static final long serialVersionUID = -8677625912384414678L;
	
	public LoanAccount() {
		super(new BigDecimal(0.0), "LOA-" + accountGen(), true);
	}
	
	private float interestRate;
	
	private BigDecimal principal;
	
	private BigDecimal minimumDue;
	
	private BigDecimal feesPaid;
	
	private Date lastPaymentDate;
	
	private Date nextPaymentDate;
	
	private Date firstPaymentDate;
	
	private int installments;
	
	private boolean isDefault;
	
	private String purpose;

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getMinimumDue() {
		return minimumDue;
	}

	public void setMinimumDue(BigDecimal minimumDue) {
		this.minimumDue = minimumDue;
	}

	public BigDecimal getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(BigDecimal feesPaid) {
		this.feesPaid = feesPaid;
	}

	public Date getLastPaymentDate() {
		return lastPaymentDate;
	}

	public void setLastPaymentDate(Date lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}

	public Date getNextPaymentDate() {
		return nextPaymentDate;
	}

	public void setNextPaymentDate(Date nextPaymentDate) {
		this.nextPaymentDate = nextPaymentDate;
	}

	public Date getFirstPaymentDate() {
		return firstPaymentDate;
	}

	public void setFirstPaymentDate(Date firstPaymentDate) {
		this.firstPaymentDate = firstPaymentDate;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int installments) {
		this.installments = installments;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

}