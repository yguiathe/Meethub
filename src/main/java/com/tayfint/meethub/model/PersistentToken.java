package com.tayfint.meethub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "persistent_logins")
public class PersistentToken implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4037408931141932031L;
	
	@Id
	@Column(name = "SERIES", unique = true, nullable = false, length = 64)
	private String series;
	
	@Column(name = "USERNAME", nullable = false, length = 64)
	private String username;
	
	@Column(name = "TOKEN", nullable = false, length = 64)
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_USED")
	private Date lastUsed;
	
	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return this.lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

}