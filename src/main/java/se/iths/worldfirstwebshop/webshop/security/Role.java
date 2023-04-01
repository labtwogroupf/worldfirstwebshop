package se.iths.worldfirstwebshop.webshop.security;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
