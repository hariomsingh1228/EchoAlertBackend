package com.echoalert.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔥 NEW: Global Unique ID (for Bluetooth mesh)
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    // Alert Title
    @Column(nullable = false)
    private String title;

    // Alert Message
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // HIGH / MEDIUM / LOW
    @Column(nullable = false)
    private String priority;

    // ACTIVE / CLOSED
    @Column(nullable = false)
    private String status;

    // Target Area / City / Zone
    private String location;

    // Alert Created Time
    private LocalDateTime createdAt;

    // 🔥 AUTO SET UUID + TIME
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        // UUID auto generate (important for mesh system)
        if (this.uuid == null) {
            this.uuid = UUID.randomUUID().toString();
        }
    }
}