package com.tayfint.meethub.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by z00382545 on 10/20/16.
 */
public class Authority implements GrantedAuthority{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
