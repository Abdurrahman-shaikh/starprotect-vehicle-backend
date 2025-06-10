package com.starprotect.security.model;

import com.starprotect.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * AdminPrinciple implements UserDetails for Admin entities
 * This class is used by Spring Security for authentication and authorization
 */
public class AdminPrinciple implements UserDetails {

    private Admin admin;

    /**
     * Constructor for AdminPrinciple
     * @param admin the admin entity
     */
    public AdminPrinciple(Admin admin) {
        this.admin = admin;
    }

    /**
     * Get the authorities granted to the admin
     * @return a collection of granted authorities (roles)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
    }

    /**
     * Get the password used to authenticate the admin
     * @return the password
     */
    @Override
    public String getPassword() {
        return admin.getAdminPassword();
    }

    /**
     * Get the username used to authenticate the admin
     * @return the username
     */
    @Override
    public String getUsername() {
        return admin.getAdminName();
    }

    /**
     * Check if the admin's account has expired
     * @return true if the admin's account is valid (non-expired), false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the admin is locked or unlocked
     * @return true if the admin is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the admin's credentials (password) has expired
     * @return true if the admin's credentials are valid (non-expired), false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if the admin is enabled or disabled
     * @return true if the admin is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
