package com.tayfint.meethub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "app_violation_type")
public class ViolationType extends BaseEntity {
	
	@Column(name= "CODE", length=3)
	private String code;
	
	@Column(name = "NAME", length = 50)
	private String name;

	@Column(name = "DESCRIPTION", length = 1500)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ViolationType [getName()=" + getName() + ", getCode()=" + getCode() + ", getDescription()="
				+ getDescription() + ", getId()=" + getId() + "]";
	}
	
}
