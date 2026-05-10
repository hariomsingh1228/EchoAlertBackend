package com.echoalert.backend.service;

import com.echoalert.backend.dto.AuthResponseDTO;
import com.echoalert.backend.dto.LoginRequestDTO;
import com.echoalert.backend.dto.RegisterDTO;
import com.echoalert.backend.entity.User;
import com.echoalert.backend.enums.UserRole;
import com.echoalert.backend.repository.UserRepository;
import com.echoalert.backend.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 🔥 REGISTER
    public AuthResponseDTO register(RegisterDTO registerDTO) {

        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(registerDTO.getName()); // 👈 IMPORTANT (tumhari entity me required hai)
        user.setEmail(registerDTO.getEmail());

        // ✅ encode password
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // ✅ FIX: ENUM ROLE HANDLE
        UserRole role = (registerDTO.getRole() == null || registerDTO.getRole().isBlank())
                ? UserRole.ROLE_USER
                : UserRole.valueOf(registerDTO.getRole());

        user.setRole(role);

        userRepository.save(user);

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name() // 👈 STRING me convert
        );

        return new AuthResponseDTO(
                token,
                "User registered successfully",
                user.getRole().name()
        );
    }

    // 🔥 LOGIN
    public AuthResponseDTO login(LoginRequestDTO request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponseDTO(
                token,
                "Login successful",
                user.getRole().name()
        );
    }
}