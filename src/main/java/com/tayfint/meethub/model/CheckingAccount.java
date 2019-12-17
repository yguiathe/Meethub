package com.tayfint.meethub.model;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHK")
public class CheckingAccount extends Account {

	private static final long serialVersionUID = 1L;

	public CheckingAccount() {
		super(new BigDecimal(0.0), "CHK-" + accountGen(), true);
	}
	
}
