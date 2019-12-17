package com.tayfint.meethub.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by z00382545 on 10/20/16.
 */

@Entity
@Table(name = "app_role")
public class Role extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> userRoles = new HashSet<>();

    public Role() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

}
