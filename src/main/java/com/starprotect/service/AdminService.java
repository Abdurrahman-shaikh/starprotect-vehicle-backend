package com.starprotect.service;

import com.starprotect.entity.Admin;
import com.starprotect.exception.InvalidCredentialsException;
import com.starprotect.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Register a new admin
     * @param admin the admin to register
     * @return the registered admin
     */
    public Admin registerAdmin(Admin admin) {
        // Encode the password before saving
        admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
        return adminRepository.save(admin);
    }

    /**
     * Authenticate an admin
     * @param adminName the admin name
     * @param password the admin password
     * @return the authenticated admin
     * @throws InvalidCredentialsException if authentication fails
     */

    public Admin authenticateAdmin(String adminName, String password) throws InvalidCredentialsException {
        Optional<Admin> adminOptional = adminRepository.findByAdminName(adminName);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (passwordEncoder.matches(password, admin.getAdminPassword())) {
                return admin;
            }
        }

        throw new InvalidCredentialsException("Invalid admin credentials");
    }


    /**
     * Get all admins
     * @return a list of all admins
     */
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    /**
     * Get an admin by ID
     * @param adminId the ID of the admin
     * @return an Optional containing the admin if found, or empty if not found
     */
    public Optional<Admin> getAdminById(Long adminId) {
        return adminRepository.findById(adminId);
    }

    /**
     * Update an admin
     * @param admin the admin to update
     * @return the updated admin
     */
    public Admin updateAdmin(Admin admin) {
        // If the password has been changed, encode it
        if (admin.getAdminPassword() != null && !admin.getAdminPassword().isEmpty()) {
            Optional<Admin> existingAdmin = adminRepository.findById(admin.getAdminId());
            if (existingAdmin.isPresent() && !existingAdmin.get().getAdminPassword().equals(admin.getAdminPassword())) {
                admin.setAdminPassword(passwordEncoder.encode(admin.getAdminPassword()));
            }
        }
        return adminRepository.save(admin);
    }

    /**
     * Delete an admin
     * @param adminId the ID of the admin to delete
     */
    public void deleteAdmin(Long adminId) {
        adminRepository.deleteById(adminId);
    }

    /**
     * Creates a default admin user if it doesn't already exist
     */
    public void createDefaultAdminIfNotExists() {
        String defaultAdminName = "admin";
        String defaultAdminPassword = "admin123";

        if (!adminRepository.existsByAdminName(defaultAdminName)) {
            Admin defaultAdmin = new Admin(defaultAdminName, defaultAdminPassword);
            Admin createdAdmin = registerAdmin(defaultAdmin);
            System.out.println("Created default admin user with username: " + defaultAdminName);
        } else {
            System.out.println("Default admin user already exists with username: " + defaultAdminName);
            adminRepository.findByAdminName(defaultAdminName);
        }
    }
}
