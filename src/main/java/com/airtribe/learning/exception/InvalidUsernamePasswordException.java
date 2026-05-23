package com.airtribe.learning.exception;

public class InvalidUsernamePasswordException extends RuntimeException {
    
    public InvalidUsernamePasswordException() {
        super("Invalid username or password");
    }
}
