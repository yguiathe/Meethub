package com.tayfint.meethub.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tayfint.meethub.model.UserRole;

@Entity
@Table(name = "app_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 3348273711654520441L;

	@Column(name = "USERNAME", length = 50)
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Transient
	private String confirmPassword;

	@Column(name = "FAILED_LOGIN_ATTEMPTS")
	private Short failedLoginAttempts;

	@Column(name = "LAST_SUCCESSFUL_LOGIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSuccessfulLogin;

	@Column(name = "BLOCKED_UNTIL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date blockedUntil;

	@Column(name = "IS_DECEASED")
	private Boolean isDeceased = false;

	@Column(name = "PRIMARY_ID", length = 40)
	private String primaryId;

	@Column(name = "PRIMARY_ID_TYPE", length = 45)
	private String primaryIdType;

	@Column(name = "BIRTHDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE)
	private Date birthdate;

	@Column(name = "FIRST_NAME", length = 45)
	private String firstName;

	@Column(name = "MIDDLE_NAME", length = 45)
	private String middleName;
	
	@Column(name = "INTRO", length = 500)
	private String intro;

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	private String lastName;

	@Column(name = "GENDER", length = 1)
	private String gender;
	
	@Column(name = "PHONE", length = 20)
	private String phone;

	@Column(name = "EDUCATION", length = 1)
	private String education;

	@Column(name = "OCCUPATION", length = 45)
	private String occupation;

	@Column(name = "MONTHLY_SALARY", precision = 22, scale = 0)
	private Double monthlySalary;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive = true;

	@Column(name = "MARITAL_STATUS_CD", length = 3)
	private String maritalStatusCd;

	@Column(name = "EMPLOYMENT_STATUS_CD", length = 3)
	private String employmentStatusCd;

	@Column(name = "EMPLOYER", length = 100)
	private String employer;

	@Column(name = "CITIZENSHIP", length = 45)
	private String citizenship;

	@Column(name = "EMAIL", length = 45)
	private String email;

	@Column(name = "TEAMS_CNT")
	private int teamsCnt;
	
	@Column(name = "APPLICATIONS_CNT")
	private int applicationsCnt;
	
	@Column(name = "AVATAR")
	@Lob
	private byte[] avatar;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles = new HashSet<>();

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

	
	public int getApplicationsCnt() {
		return applicationsCnt;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public Date getBlockedUntil() {
		return this.blockedUntil;
	}

	public String getCitizenship() {
		return this.citizenship;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getEducation() {
		return this.education;
	}

	public String getEmail() {
		return email;
	}

	public String getEmployer() {
		return this.employer;
	}

	public String getEmploymentStatusCd() {
		return this.employmentStatusCd;
	}

	public Short getFailedLoginAttempts() {
		return this.failedLoginAttempts;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public String getIntro() {
		return this.intro;
	}

	public Boolean getIsDeceased() {
		return this.isDeceased;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public Date getLastSuccessfulLogin() {
		return this.lastSuccessfulLogin;
	}

	public String getMaritalStatusCd() {
		return this.maritalStatusCd;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public Double getMonthlySalary() {
		return this.monthlySalary;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhone() {
		return phone;
	}

	public String getPrimaryId() {
		return this.primaryId;
	}

	public String getPrimaryIdType() {
		return this.primaryIdType;
	}

	public int getTeamsCnt() {
		return teamsCnt;
	}

	public String getUsername() {
		return this.username;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setApplicationsCnt(int applicationsCnt) {
		this.applicationsCnt = applicationsCnt;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBlockedUntil(Date blockedUntil) {
		this.blockedUntil = blockedUntil;
	}
	
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public void setEmploymentStatusCd(String employmentStatusCd) {
		this.employmentStatusCd = employmentStatusCd;
	}

	public void setFailedLoginAttempts(Short failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public void setIsDeceased(Boolean isDeceased) {
		this.isDeceased = isDeceased;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLastSuccessfulLogin(Date lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin;
	}

	public void setMaritalStatusCd(String maritalStatusCd) {
		this.maritalStatusCd = maritalStatusCd;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}

	public void setPrimaryIdType(String primaryIdType) {
		this.primaryIdType = primaryIdType;
	}

	public void setTeamsCnt(int teamsCnt) {
		this.teamsCnt = teamsCnt;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (UserRole ur : userRoles) {
			authorities.add(new Authority(ur.getRole().getName()));
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getFnLN() {
		return getFirstName() + " " + getLastName();
	}
	
}
