package com.starprotect.service;

import com.starprotect.entity.Underwriter;
import com.starprotect.exception.InvalidCredentialsException;
import com.starprotect.security.model.UserPrinciple;
import com.starprotect.repository.UnderwriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnderwriterService implements UserDetailsService {

    private final UnderwriterRepository underwriterRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UnderwriterService(UnderwriterRepository underwriterRepository, PasswordEncoder passwordEncoder) {
        this.underwriterRepository = underwriterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create a new underwriter
     * @param underwriter the underwriter to create
     * @return the created underwriter
     */
    public Underwriter createUnderwriter(Underwriter underwriter) {
        // Encode the password before saving
        underwriter.setPassword(passwordEncoder.encode(underwriter.getPassword()));
        return underwriterRepository.save(underwriter);
    }

    /**
     * Get an underwriter by ID
     * @param id the ID of the underwriter to retrieve
     * @return an Optional containing the underwriter if found, or empty if not found
     */
    public Optional<Underwriter> getUnderwriterById(Long id) {
        return underwriterRepository.findById(id);
    }

    /**
     * Get all underwriters
     * @return a list of all underwriters
     */
    public List<Underwriter> getAllUnderwriters() {
        return underwriterRepository.findAll();
    }

    /**
     * Load a user by username
     * @param username the username to load
     * @return UserDetails for the found user
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Underwriter underwriter = underwriterRepository.findByName(username);

        if (underwriter == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrinciple(underwriter);
    }

    /**
     * Get the currently logged-in underwriter
     * @return the currently logged-in underwriter
     */
    public Underwriter getCurrentLoggedInUnderwriter() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return underwriterRepository.findByName(username);
    }

    /**
     * Delete an underwriter
     * @param id the ID of the underwriter to delete
     */
    public void deleteUnderwriter(Long id) {
        underwriterRepository.deleteById(id);
    }

    /**
     * Update an underwriter
     * @param underwriter the underwriter to update
     * @return the updated underwriter
     */
    public Underwriter updateUnderwriter(Underwriter underwriter) {
        // If the password has been changed, encode it
        if (underwriter.getPassword() != null && !underwriter.getPassword().isEmpty()) {
            Optional<Underwriter> existingUnderwriter = underwriterRepository.findById(underwriter.getUnderwriterId());
            // && !existingUnderwriter.get().getPassword().equals(underwriter.getPassword())
            if (existingUnderwriter.isPresent()) {
                underwriter.setPassword(passwordEncoder.encode(underwriter.getPassword()));
            }
        }
        return underwriterRepository.save(underwriter);
    }

    /**
     * Authenticate an underwriter
     * @param name the underwriter name
     * @param password the underwriter password
     * @return the authenticated underwriter
     * @throws InvalidCredentialsException if authentication fails
     */
    public Underwriter authenticateUnderwriter(String name, String password) throws InvalidCredentialsException {
        Underwriter underwriter = underwriterRepository.findByName(name);

        if (underwriter != null) {
            if (passwordEncoder.matches(password, underwriter.getPassword())) {
                return underwriter;
            }
        }

        throw new InvalidCredentialsException("Invalid underwriter credentials");
    }
}
