package com.tayfint.meethub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "app_transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction extends BaseEntity {
	
	@Column(name = "availableBalance")
	private BigDecimal availableBalance;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "originator_name")
	private String originatorName;
	
	@Column(name = "direction")
	private String direction;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "notes", length=1000)
	private String notes;
	
	@Column(name = "src_acct")
	private Long srcAcct;
	
	@Column(name = "dest_acct")
	private Long destAcct;
	
	@CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name="transaction_date_time", nullable = false, updatable = false)
    private LocalDateTime created;

    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modified;
    
    @ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Transaction(BigDecimal availableBalance, BigDecimal amount, String status, String type, String originatorName, String direction,
			String description, Long srcAcct, Long destAcct, Account account, String notes) {
		super();
		this.availableBalance = availableBalance;
		this.amount = amount;
		this.status = status;
		this.type = type;
		this.originatorName = originatorName;
		this.direction = direction;
		this.description = description;
		this.srcAcct = srcAcct;
		this.destAcct = destAcct;
		this.account = account;
		this.notes = notes;
	}

	
	
	public Transaction(BigDecimal availableBalance, BigDecimal amount, String status, String type,
			String originatorName, String direction, String description, Long destAcct, Account account, String notes) {
		super();
		this.availableBalance = availableBalance;
		this.amount = amount;
		this.status = status;
		this.type = type;
		this.originatorName = originatorName;
		this.direction = direction;
		this.description = description;
		this.destAcct = destAcct;
		this.account = account;
		this.notes = notes;
	}
	
	public Transaction() {
		super();
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOriginatorName() {
		return originatorName;
	}

	public void setOriginatorName(String originatorName) {
		this.originatorName = originatorName;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getSrcAcct() {
		return srcAcct;
	}

	public void setSrcAcct(Long srcAcct) {
		this.srcAcct = srcAcct;
	}

	public Long getDestAcct() {
		return destAcct;
	}

	public void setDestAcct(Long destAcct) {
		this.destAcct = destAcct;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
    
}
