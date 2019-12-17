package com.tayfint.meethub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Lob;
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
@Table(name = "app_meeting")
@EntityListeners(AuditingEntityListener.class)
public class Meeting extends BaseEntity {

	@Column(name = "NAME", unique = true, length = 45)
	private String name;

	@Column(name = "SHORT_DESC", length = 1500)
	private String shortDesc;

	@Column(name = "LOGO", length = 100000)
	@Lob
	private byte[] logo;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CLOSE_DATE")
	private Date closeDate;

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

	@Column(name = "TERMS", length = 17000000 )
	@Lob
	private byte[] terms;

	@Column(name = "COUNTRY_OF_INCORP", length = 45)
	private String countryOfIncorp;

	@Column(name = "PRIMARY_ID_TYPE", length = 45)
	private String primaryIdType;

	@Column(name = "PRIMARY_ID", length = 40)
	private String primaryId;

	@Column(name = "STREET_ADDR", length = 40)
	private String streetAddr;

	@Column(name = "CITY", length = 20)
	private String city;

	@Column(name = "PROVINCE", length = 20)
	private String province;

	@Column(name = "ZIP", length = 10)
	private String zip;

	@Column(name = "MEMBERS_CNT")
	private int membersCnt;

	@Column(name = "IS_PUBLIC")
	private boolean isPublic = true;
	
	@Column(name = "MIN_CONTRIBUTION")
	private BigDecimal minContribution;

	@OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
	private Set<Account> accounts = new HashSet<Account>();

	public void addAccount(Account account) {
		accounts.add(account);
		account.setMeeting(this);
	}

	public void removeAccount(Account account) {
		accounts.remove(account);
		account.setMeeting(null);
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public String getCountryOfIncorp() {
		return countryOfIncorp;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public byte[] getLogo() {
		return logo;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public String getName() {
		return name;
	}

	public String getPrimaryId() {
		return primaryId;
	}

	public String getPrimaryIdType() {
		return primaryIdType;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public byte[] getTerms() {
		return terms;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public void setCountryOfIncorp(String countryOfIncorp) {
		this.countryOfIncorp = countryOfIncorp;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrimaryId(String primaryId) {
		this.primaryId = primaryId;
	}

	public void setPrimaryIdType(String primaryIdType) {
		this.primaryIdType = primaryIdType;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public void setTerms(byte[] terms) {
		this.terms = terms;
	}

	public int getMembersCnt() {
		return membersCnt;
	}

	public void setMembersCnt(int membersCnt) {
		this.membersCnt = membersCnt;
	}

	public String getStreetAddr() {
		return streetAddr;
	}

	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public BigDecimal getMinContribution() {
		return minContribution;
	}

	public void setMinContribution(BigDecimal minContribution) {
		this.minContribution = minContribution;
	}

}
