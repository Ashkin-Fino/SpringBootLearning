package com.airtribe.learning.service;

import com.airtribe.learning.dto.UserResponse;
import com.airtribe.learning.entity.User;
import com.airtribe.learning.exception.ResourceNotFoundException;
import com.airtribe.learning.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() ->
                new ResourceNotFoundException("User not found with username: " + username)
            );
        return mapToUserResponseDto(user);
    }

    private UserResponse mapToUserResponseDto(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getAge(),
            user.getPhoneNumber(),
            user.getEmail(),
            user.getRole()
        );
    }
}
