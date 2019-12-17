package com.tayfint.meethub.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "app_account")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "acctType", length = 3)
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -3915917470228586571L;
	
	public static SimpleDateFormat dft = new SimpleDateFormat("yyMMddhhmmssMs");
	
	public Account(BigDecimal balance, String accountNumber, boolean isActive) {
		super();
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.isActive = isActive;
	}

	@Column(name = "balance")
	private BigDecimal balance = new BigDecimal("0");
	
	@Column(name = "last_stmt_bal")
	private BigDecimal lastStmtBalance = new BigDecimal("0");
	
	@Column(name = "overdraft_limit")
	private BigDecimal overdraftLimit = new BigDecimal("0");
	
	@Column(name = "ACCOUNT_NUMBER", length = 20)
	private String accountNumber;
	
	@Column(name = "acctType", insertable = false, updatable = false, length =3)
	private String acctType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CLOSE_DATE")
	private Date closeDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "next_stmt")
	private Date nextStmt;
	
	@Column(name = "IS_ACTIVE")
	private boolean isActive = true;
	
	@CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modified;
	
	@ManyToOne
	@JoinColumn(name = "meeting_id")
    private Meeting meeting;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id")
    private Membership membership;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new HashSet<Transaction>();
	
	public Set<Transaction> getTransactions() {
		return this.transactions;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
		transaction.setAccount(this);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAcctType() {
		return acctType;
	}

	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public static String accountGen() {
		Date dNow = new Date();
        return dft.format(dNow);
    }

	public BigDecimal getLastStmtBalance() {
		return lastStmtBalance;
	}

	public void setLastStmtBalance(BigDecimal lastStmtBalance) {
		this.lastStmtBalance = lastStmtBalance;
	}

	public BigDecimal getOverdraftLimit() {
		return overdraftLimit;
	}

	public void setOverdraftLimit(BigDecimal overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	public Date getNextStmt() {
		return nextStmt;
	}

	public void setNextStmt(Date nextStmt) {
		this.nextStmt = nextStmt;
	}
	
	public String getTypeDescription() {
		String desc = "";
		switch (this.acctType) {
		case "CHK":
			desc = "Checking";
			break;
		case "SAV":
			desc = "Savings";
			break;
		case "LOA":
			desc = "Loans";
			break;
		case "INV":
			desc = "Investment";
			break;
		default:
			desc = "Others";
			break;
		}
		return desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((acctType == null) ? 0 : acctType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (acctType == null) {
			if (other.acctType != null)
				return false;
		} else if (!acctType.equals(other.acctType))
			return false;
		return true;
	}
	
}
