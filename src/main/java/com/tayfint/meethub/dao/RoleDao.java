package com.tayfint.meethub.dao;

import org.springframework.data.repository.CrudRepository;

import com.tayfint.meethub.model.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
