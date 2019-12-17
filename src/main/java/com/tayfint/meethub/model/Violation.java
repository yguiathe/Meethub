package com.tayfint.meethub.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "app_violation")
@EntityListeners(AuditingEntityListener.class)
public class Violation extends BaseEntity {

	@Column(name = "membership_id")
	private Long membership_id;
	
	@Column(name = "violation_type_id")
	private Long violation_type_id;
	
	@Column(name = "description", length = 1500)
	private String description;
	
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

	public Long getMembership_id() {
		return membership_id;
	}

	public void setMembership_id(Long membership_id) {
		this.membership_id = membership_id;
	}

	public Long getViolation_type_id() {
		return violation_type_id;
	}

	public void setViolation_type_id(Long violation_type_id) {
		this.violation_type_id = violation_type_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Violation [getMembership_id()=" + getMembership_id() + ", getViolation_type_id()="
				+ getViolation_type_id() + ", getDescription()=" + getDescription() + ", getId()=" + getId() + "]";
	}
	
}
