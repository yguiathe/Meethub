package com.tayfint.meethub.model;

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
@Table(name="app_application")
@EntityListeners(AuditingEntityListener.class)
public class Application extends BaseEntity {

	
	public Application(Meeting meeting, User user) {
		super();
		this.statusCd = "0";
		this.meeting = meeting;
		this.user = user;
	}

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
	
	@Column(nullable = true)
	private LocalDateTime approved;
	
	@Column(name = "approved_by", length = 50)
	private String approvedBy;
	
	@Column(name = "STATUS_CD", length = 1)
	private String statusCd;
	
	@Column(name = "notes", length = 1000)
	private String notes;
	
	@ManyToOne
	@JoinColumn(name = "MEETING_ID")
	private Meeting meeting;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

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

	public LocalDateTime getApproved() {
		return approved;
	}

	public void setApproved(LocalDateTime approved) {
		this.approved = approved;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
}
