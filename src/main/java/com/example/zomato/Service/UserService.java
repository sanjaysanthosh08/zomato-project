package com.example.zomato.Service;

import com.example.zomato.Entity.UserEntity;
import com.example.zomato.Repository.UserRepository;
import com.example.zomato.dto.SignupRequest;

import jakarta.validation.Valid;

import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Register using SignupRequest DTO
    public String register(SignupRequest signupRequest) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(signupRequest.getUsername());
        if (existingUser.isPresent()) {
            return "Username already exists";
        }

        Optional<UserEntity> existingEmail = userRepository.findByEmail(signupRequest.getEmail());
        if (existingEmail.isPresent()) {
            return "Email already registered";
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(signupRequest.getUsername());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        userRepository.save(newUser);
        return "User registered successfully";
    }

    // ✅ Authenticate user
    public boolean authenticate(String username, String rawPassword) {
        Optional<UserEntity> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            return passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
        }
        return false;
    }

    // ✅ Get user by username
    public Optional<UserEntity> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ✅ Used by Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                java.util.Collections.emptyList()
        );
    }
}
