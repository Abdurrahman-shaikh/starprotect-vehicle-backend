package com.starprotect.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "underwriters")
public class Underwriter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long underwriterId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate dateOfJoining;

    @Column(nullable = false)
    private String password;

    public Long getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(Long underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Underwriter() {
    }

    public Underwriter(Long underwriterId, String name, LocalDate dateOfBirth, LocalDate dateOfJoining, String password) {
        this.underwriterId = underwriterId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.password = password;
    }

    public Underwriter(String name, LocalDate dateOfBirth, LocalDate dateOfJoining, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.password = password;
    }

}
