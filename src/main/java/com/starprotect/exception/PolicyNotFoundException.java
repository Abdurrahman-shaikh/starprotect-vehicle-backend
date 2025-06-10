package com.starprotect.exception;

public class PolicyNotFoundException extends RuntimeException {
    public PolicyNotFoundException(String policyNotFound) {
        super(policyNotFound);
    }
}
