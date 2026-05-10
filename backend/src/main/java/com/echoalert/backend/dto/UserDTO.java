package com.echoalert.backend.dto;

import com.echoalert.backend.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // Optional while update
    private String password;

    private String phone;

    private String location;

    // 🔥 FIX: role null na aaye
    @NotNull(message = "Role is required")
    private UserRole role;

    private boolean enabled;
}