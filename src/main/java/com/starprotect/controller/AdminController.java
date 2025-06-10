package com.starprotect.controller;

import java.util.List;
import java.util.Optional;

import com.starprotect.entity.Admin;
import com.starprotect.entity.Underwriter;
import com.starprotect.exception.InvalidCredentialsException;
import com.starprotect.service.AdminService;
import com.starprotect.service.UnderwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UnderwriterService underwriterService;
    private final AdminService adminService;

    /**
     * Constructor for AdminController
     * @param underwriterService the underwriter service
     * @param adminService the admin service
     */
    @Autowired
    public AdminController(UnderwriterService underwriterService, AdminService adminService) {
        this.underwriterService = underwriterService;
        this.adminService = adminService;
    }

    /**
     * Register a new admin
     * @param admin the admin to register
     * @return ResponseEntity containing the registered admin
     */
    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.registerAdmin(admin));
    }

    /**
     * Login an admin
     * Example curl command for admin login:
     * curl -X POST "http://localhost:8080/api/admin/login?adminName=adminuser&adminPassword=password123" \
     * -H "Content-Type: application/x-www-form-urlencoded"
     * 
     * @param adminName the admin name
     * @param adminPassword the admin password
     * @return ResponseEntity containing the authenticated admin or error message
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestParam String adminName, @RequestParam String adminPassword) {
        try {
            Admin admin = adminService.authenticateAdmin(adminName, adminPassword);
            return ResponseEntity.ok(admin);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    /**
     * Update an underwriter
     * @param id the ID of the underwriter to update
     * @param underwriter the updated underwriter data
     * @return ResponseEntity containing the updated underwriter
     */
    @PutMapping("/underwriters/{id}")
    public ResponseEntity<Underwriter> updateUnderwriter(
            @PathVariable Long id,
            @RequestBody Underwriter underwriter) {
        underwriter.setUnderwriterId(id);
        return ResponseEntity.ok(underwriterService.updateUnderwriter(underwriter));
    }

    /**
     * Delete an underwriter
     * @param id the ID of the underwriter to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/underwriters/{id}")
    public ResponseEntity<Void> deleteUnderwriter(@PathVariable Long id) {
        underwriterService.deleteUnderwriter(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get all underwriters
     * @return ResponseEntity containing a list of all underwriters
     */
    @GetMapping("/underwriters")
    public ResponseEntity<List<Underwriter>> getAllUnderwriters() {
        return ResponseEntity.ok(underwriterService.getAllUnderwriters());
    }

    /**
     * Get an underwriter by ID
     * @param id the ID of the underwriter to retrieve
     * @return ResponseEntity containing the underwriter if found
     */
    @GetMapping("/underwriters/{id}")
    public ResponseEntity<Optional<Underwriter>> getUnderwriterById(@PathVariable Long id) {
        return ResponseEntity.ok(underwriterService.getUnderwriterById(id));
    }

    /**
     * Get all admins
     * @return ResponseEntity containing a list of all admins
     */
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    /**
     * Get an admin by ID
     * @param id the ID of the admin to retrieve
     * @return ResponseEntity containing the admin if found
     */
    @GetMapping("/admins/{id}")
    public ResponseEntity<Optional<Admin>> getAdminById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    /**
     * Update an admin
     * @param id the ID of the admin to update
     * @param admin the updated admin data
     * @return ResponseEntity containing the updated admin
     */
    @PutMapping("/admins/{id}")
    public ResponseEntity<Admin> updateAdmin(
            @PathVariable Long id,
            @RequestBody Admin admin) {
        admin.setAdminId(id);
        return ResponseEntity.ok(adminService.updateAdmin(admin));
    }

    /**
     * Delete an admin
     * @param id the ID of the admin to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/admins/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }
}
