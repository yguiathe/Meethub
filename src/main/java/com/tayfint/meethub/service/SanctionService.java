package com.tayfint.meethub.service;

public interface SanctionService {

	void apply();

	void update();

	void getType();

	void getStatus();

	void getExpirationDate();

	void getStartDate();

	void cancel();

	void getFee();

	void list();
}
