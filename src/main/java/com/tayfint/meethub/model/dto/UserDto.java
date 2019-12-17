package com.tayfint.meethub.model.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Date lastSuccessfulLogin;

	private Boolean isDeceased;

	private String primaryId;

	private String primaryIdType;

	private Date birthdate;

	private String firstName;

	private String middleName;
	
	private String intro;

	private String lastName;

	private String gender;
	
	private String email;

	private int teamsCnt;

	private int applicationsCnt;

	private String avatar;
	
	private String employer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastSuccessfulLogin() {
		return lastSuccessfulLogin;
	}

	public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin;
	}

	public Boolean getIsDeceased() {
		return isDeceased;
	}

	public void setIsDeceased(Boolean isDeceased) {
		this.isDeceased = isDeceased;
	}

	public String getPrimaryId() {
		return primaryId;
	}

	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}

	public String getPrimaryIdType() {
		return primaryIdType;
	}

	public void setPrimaryIdType(String primaryIdType) {
		this.primaryIdType = primaryIdType;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTeamsCnt() {
		return teamsCnt;
	}

	public void setTeamsCnt(int teamsCnt) {
		this.teamsCnt = teamsCnt;
	}

	public int getApplicationsCnt() {
		return applicationsCnt;
	}

	public void setApplicationsCnt(int applicationsCnt) {
		this.applicationsCnt = applicationsCnt;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public void setAvatar(byte[] avatar) {
		try {
			this.avatar = avatar.length < 1 ? null : new String(java.util.Base64.getEncoder().encode(avatar), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.avatar = "";
		}
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

}
