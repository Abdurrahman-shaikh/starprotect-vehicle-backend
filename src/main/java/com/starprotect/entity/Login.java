package com.starprotect.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {
    
    @Id
    private Long username;
    
    private String password;
    private String role; // "ADMIN" or "UNDERWRITER"
    
    @OneToOne
    @JoinColumn(name = "underwriter_id")
    private Underwriter underwriter;
}