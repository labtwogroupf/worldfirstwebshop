package se.iths.worldfirstwebshop.webshop.application.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    public static final String ADMIN_AUTHORITY = "ADMIN";


    @Override
    public String getAuthority() {
        return this.name();
    }
}
