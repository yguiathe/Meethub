package com.tayfint.meethub.model;

import java.math.BigDecimal;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INV")
public class InvestmentAccount extends Account {

	private static final long serialVersionUID = -7561662185174613784L;
	
	public InvestmentAccount() {
		super(new BigDecimal(0.0), "INV-" + accountGen(), true);
	}
	
	private BigDecimal totalCost;
	
	private String projectName;
	
	private String projectDescription;

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

}
