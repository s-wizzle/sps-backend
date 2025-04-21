package com.wizzle.sps_backend.exception;

public class SpsGameNotFoundException extends RuntimeException {
    public SpsGameNotFoundException(String message) {
        super(message);
    }
}
