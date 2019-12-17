package com.tayfint.meethub.dao;

import org.springframework.data.repository.CrudRepository;

import com.tayfint.meethub.model.Setting;

public interface SettingDao extends CrudRepository<Setting, Long> {

}
