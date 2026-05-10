package com.echoalert.backend.entity;

import com.echoalert.backend.enums.UserRole; // 👈 ye import add kiya
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Full Name
    @Column(nullable = false)
    private String name;

    // Unique Email
    @Column(nullable = false, unique = true)
    private String email;

    // Encrypted Password
    @Column(nullable = false)
    private String password;

    // ✅ UPDATED ROLE FIELD (IMPORTANT CHANGE)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    // Optional Contact Number
    private String phone;

    // Optional User Location / City
    private String location;

    // Active / Inactive Account
    private boolean enabled = true;

    // Registration Time
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}