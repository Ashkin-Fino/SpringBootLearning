package com.airtribe.learning.controller;

import com.airtribe.learning.dto.LoginUserRequest;
import com.airtribe.learning.dto.LoginUserResponse;
import com.airtribe.learning.dto.RegisterUserRequest;
import com.airtribe.learning.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserRequest request) {

        authService.register(request);

        return ResponseEntity.status(200).body("User Registered Successfully"); // 200 OK
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest request) {

        LoginUserResponse response = authService.login(request);

        return ResponseEntity.status(200).body(response); // 200 OK
    }
}
