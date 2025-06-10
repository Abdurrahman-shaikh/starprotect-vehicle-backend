package com.starprotect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controller for handling logout functionality
 * This controller provides endpoints for both admin and underwriter logout
 */
@RestController
@RequestMapping("/api")
public class LogoutController {

    /**
     * Logout the current user (admin or underwriter)
     * This endpoint invalidates the session and clears the security context
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @return ResponseEntity with success message
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    /**
     * Logout an admin user
     * This endpoint is specifically for admin users
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @return ResponseEntity with success message
     */
    @PostMapping("/admin/logout")
    public ResponseEntity<String> adminLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Admin logged out successfully");
    }

    /**
     * Logout an underwriter user
     * This endpoint is specifically for underwriter users
     * 
     * @param request the HTTP request
     * @param response the HTTP response
     * @return ResponseEntity with success message
     */
    @PostMapping("/v1/underwriter/logout")
    public ResponseEntity<String> underwriterLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok("Underwriter logged out successfully");
    }
}