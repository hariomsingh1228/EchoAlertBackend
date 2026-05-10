package com.echoalert.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Related Alert Id (optional)
    private Long alertId;

    // Receiver User Id (optional)
    private Long userId;

    // Email / PUSH / SMS / IN_APP
    @Column(nullable = false)
    private String type;

    // Notification Title
    @Column(nullable = false)
    private String title;

    // Notification Message
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // SENT / FAILED / PENDING
    @Column(nullable = false)
    private String status;

    // Receiver Address (email/mobile/device token)
    private String recipient;

    // Delivery Time
    private LocalDateTime sentAt;

    @PrePersist
    public void prePersist() {
        if (this.sentAt == null) {
            this.sentAt = LocalDateTime.now();
        }
    }
}