package com.starprotect.security.model;

import com.starprotect.entity.Underwriter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * UserPrinciple implements UserDetails for Underwriter entities
 * This class is used by Spring Security for authentication and authorization
 */
public class UserPrinciple implements UserDetails {

    private Underwriter underwriter;

    /**
     * Constructor for UserPrinciple
     * @param underwriter the underwriter entity
     */
    public UserPrinciple(Underwriter underwriter) {
        this.underwriter = underwriter;
    }

    /**
     * Get the authorities granted to the user
     * @return a collection of granted authorities (roles)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    /**
     * Get the password used to authenticate the user
     * @return the password
     */
    @Override
    public String getPassword() {
        return underwriter.getPassword();
    }

    /**
     * Get the username used to authenticate the user
     * @return the username
     */
    @Override
    public String getUsername() {
        return underwriter.getName();
    }

    /**
     * Check if the user's account has expired
     * @return true if the user's account is valid (non-expired), false otherwise
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Check if the user is locked or unlocked
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Check if the user's credentials (password) has expired
     * @return true if the user's credentials are valid (non-expired), false otherwise
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Check if the user is enabled or disabled
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
