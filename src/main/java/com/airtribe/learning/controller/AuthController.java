package com.airtribe.learning.controller;

import com.airtribe.learning.dto.RegisterUserRequest;
import com.airtribe.learning.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterUserRequest request) {

        authService.register(request);

        return "User registered successfully";
    }
}
