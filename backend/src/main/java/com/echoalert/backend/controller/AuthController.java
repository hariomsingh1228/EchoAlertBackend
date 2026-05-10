package com.echoalert.backend.controller;

import com.echoalert.backend.dto.AuthResponseDTO;
import com.echoalert.backend.dto.LoginRequestDTO;
import com.echoalert.backend.dto.RegisterDTO;
import com.echoalert.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Register New User / Admin
    @PostMapping("/register")
    public AuthResponseDTO register(
            @Valid @RequestBody RegisterDTO registerDTO) {

        return authService.register(registerDTO);
    }

    // Login User / Admin
    @PostMapping("/login")
    public AuthResponseDTO login(
            @Valid @RequestBody LoginRequestDTO loginRequestDTO) {

        return authService.login(loginRequestDTO);
    }
}