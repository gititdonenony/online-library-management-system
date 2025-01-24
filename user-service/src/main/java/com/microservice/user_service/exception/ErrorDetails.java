package com.microservice.user_service.exception;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;

    public ErrorDetails(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
