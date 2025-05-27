package com.example.zomato.Service;

import com.example.zomato.dto.JwtResponse;
import com.example.zomato.dto.LoginRequest;
import com.example.zomato.dto.SignupRequest;
import com.example.zomato.Entity.UserEntity;
import com.example.zomato.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(SignupRequest signupRequest) {
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            return "Username already exists";
        }

        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            return "Email already registered";
        }

        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        userRepository.save(user);
        return "User registered successfully";
    }

    public JwtResponse login(LoginRequest loginRequest) {
        Optional<UserEntity> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                return new JwtResponse(token);
            }
        }
        throw new RuntimeException("Invalid credentials");
    }
}
