package com.eduConnect.eduConnect.Exception;

@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

