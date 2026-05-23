package com.airtribe.learning.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    // getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
