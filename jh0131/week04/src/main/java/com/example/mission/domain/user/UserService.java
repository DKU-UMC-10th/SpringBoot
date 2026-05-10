package com.example.mission.domain.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for user operations.  Handles registration and retrieval of users.
 * Business rules such as preventing duplicate emails could be added here.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User registerUser(String email, String password, String nickname, String phone) {
        // Check if email is already taken.  In a real application you should
        // hash the password and validate the inputs.
        userRepository.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("Email already in use: " + email);
        });
        User user = new User(email, password, nickname, phone);
        return userRepository.save(user);
    }
}