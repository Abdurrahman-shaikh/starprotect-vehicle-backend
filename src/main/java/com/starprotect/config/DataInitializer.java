package com.starprotect.config;

import com.starprotect.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component that initializes default data when the application starts
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final AdminService adminService;

    @Autowired
    public DataInitializer(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(String... args) {
        // Create default admin user if it doesn't exist
        try {
            // Use the method we created which handles the existence check internally
            adminService.createDefaultAdminIfNotExists();
            System.out.println("Default admin user check completed successfully");
        } catch (Exception e) {
            System.err.println("Error creating default admin user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
