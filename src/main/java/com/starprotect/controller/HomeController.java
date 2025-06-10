package com.starprotect.controller;

import com.starprotect.entity.Underwriter;
import com.starprotect.exception.InvalidCredentialsException;
import com.starprotect.service.UnderwriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling underwriter-related operations
 * This controller provides endpoints for underwriter registration, login, and information retrieval
 */
@RestController
@RequestMapping("/api/v1/underwriter")
public class HomeController {

    @Autowired
    private UnderwriterService underwriterService;


//    @GetMapping("/session")
//    public String getSessionId(HttpSession session) {
//        return session.getId();
//    }

    /**
     * Register a new underwriter
     * @param underwriter the underwriter to register
     * @return the registered underwriter
     */
    @PostMapping("register")
    public Underwriter registerUnderwriter(@RequestBody Underwriter underwriter) {
        return underwriterService.createUnderwriter(underwriter);
    }

    /**
     * Get all underwriters
     * @return a list of all underwriters
     */
    @GetMapping("get_all")
    public List<Underwriter> getAllUnderwriters() {
        return underwriterService.getAllUnderwriters();
    }

    /**
     * Get the currently logged-in underwriter
     * @return the currently logged-in underwriter
     */
    @GetMapping("get_logged_in")
    public Underwriter getCurrentLoggedInUnderwriter() {
        return underwriterService.getCurrentLoggedInUnderwriter();
    }

    /**
     * Login an underwriter
     * This endpoint authenticates an underwriter with the given credentials
     * 
     * Example curl command for underwriter login:
     * curl -X POST "http://localhost:8080/api/v1/underwriter/login?name=underwriter&password=password123" \
     * -H "Content-Type: application/x-www-form-urlencoded"
     * 
     * @param name the underwriter name
     * @param password the underwriter password
     * @return ResponseEntity containing the authenticated underwriter or error message
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUnderwriter(
            @RequestParam String name,
            @RequestParam String password
//            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader
    ) {
        try {
            Underwriter underwriter = underwriterService.authenticateUnderwriter(name, password);
            return ResponseEntity.ok(underwriter);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
