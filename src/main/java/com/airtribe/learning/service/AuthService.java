package com.airtribe.learning.service;

import com.airtribe.learning.dto.LoginUserRequest;
import com.airtribe.learning.dto.RegisterUserRequest;
import com.airtribe.learning.entity.Role;
import com.airtribe.learning.entity.User;
import com.airtribe.learning.exception.InvalidUsernamePasswordException;
import com.airtribe.learning.exception.ResourceAlreadyExists;
import com.airtribe.learning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(RegisterUserRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResourceAlreadyExists("Username already exists");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceAlreadyExists("Email already exists");
        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    public void login(LoginUserRequest request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new InvalidUsernamePasswordException());
    
        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );
    
        if (!matches) {
            throw new InvalidUsernamePasswordException();
        }
    }
}
