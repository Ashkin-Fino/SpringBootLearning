package com.airtribe.learning.dto;

public class LoginUserResponse {

    private String token;

    public LoginUserResponse() {
    }

    public LoginUserResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}