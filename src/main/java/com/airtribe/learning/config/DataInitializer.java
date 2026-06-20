package com.airtribe.learning.config;

import com.airtribe.learning.entity.Role;
import com.airtribe.learning.entity.User;
import com.airtribe.learning.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isPresent()) {
            return;
        }

        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));

        admin.setFirstName("System");
        admin.setLastName("Administrator");
        admin.setAge(30);
        admin.setPhoneNumber("9999999999");
        admin.setEmail("admin@travel.com");

        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        logger.info("Admin user created");
    }
}
