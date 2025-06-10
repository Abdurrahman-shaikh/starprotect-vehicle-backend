package com.starprotect.repository;

import com.starprotect.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    /**
     * Find an admin by their name
     * @param adminName the name of the admin
     * @return an Optional containing the admin if found, or empty if not found
     */
    Optional<Admin> findByAdminName(String adminName);
    
    /**
     * Check if an admin exists with the given name
     * @param adminName the name of the admin
     * @return true if an admin exists with the given name, false otherwise
     */
    boolean existsByAdminName(String adminName);
}