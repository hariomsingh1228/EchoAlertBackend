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

    // Global Unique ID for Bluetooth mesh
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String priority;

    @Column(nullable = false)
    private String status;

    private String location;

    private LocalDateTime createdAt;

    // Push notification sent status
    @Column(nullable = false)
    private Boolean sent = false;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }

        if (this.uuid == null || this.uuid.isBlank()) {
            this.uuid = UUID.randomUUID().toString();
        }

        if (this.status == null || this.status.isBlank()) {
            this.status = "ACTIVE";
        }

        if (this.sent == null) {
            this.sent = false;
        }
    }
}