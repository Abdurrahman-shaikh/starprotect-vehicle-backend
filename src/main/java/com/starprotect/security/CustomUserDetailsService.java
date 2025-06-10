package com.starprotect.security;

import com.starprotect.entity.Admin;
import com.starprotect.entity.Underwriter;
import com.starprotect.security.model.AdminPrinciple;
import com.starprotect.security.model.UserPrinciple;
import com.starprotect.repository.AdminRepository;
import com.starprotect.repository.UnderwriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final UnderwriterRepository underwriterRepository;

    /**
     * Constructor for CustomUserDetailsService
     * @param adminRepository the admin repository
     * @param underwriterRepository the underwriter repository
     */
    @Autowired
    public CustomUserDetailsService(AdminRepository adminRepository, UnderwriterRepository underwriterRepository) {
        this.adminRepository = adminRepository;
        this.underwriterRepository = underwriterRepository;
    }

    /**
     * Load a user by username
     * This method first tries to find an admin with the given username.
     * If no admin is found, it tries to find an underwriter with the given username.
     * If no user is found, it throws a UsernameNotFoundException.
     * 
     * @param username the username to load
     * @return UserDetails for the found user
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First, try to find an admin with the given username
        Optional<Admin> adminOptional = adminRepository.findByAdminName(username);
        if (adminOptional.isPresent()) {
            return new AdminPrinciple(adminOptional.get());
        }

        // If no admin is found, try to find an underwriter with the given username
        Underwriter underwriter = underwriterRepository.findByName(username);
        if (underwriter != null) {
            return new UserPrinciple(underwriter);
        }

        // If no user is found, throw an exception
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
