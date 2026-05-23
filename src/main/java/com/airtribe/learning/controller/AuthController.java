package com.airtribe.learning.controller;

import com.airtribe.learning.dto.LoginUserRequest;
import com.airtribe.learning.dto.RegisterUserRequest;
import com.airtribe.learning.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {

        authService.register(request);

        return ResponseEntity.status(200).body("User Registered Successfully"); // 200 OK
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserRequest request) {

        authService.login(request);

        return ResponseEntity.status(200).body("Login successful"); // 200 OK
    }
}
