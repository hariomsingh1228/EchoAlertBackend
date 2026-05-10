package com.echoalert.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AlertDTO {

    // 🔥 NEW (for mesh system - optional in request)
    private String uuid;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    // HIGH / MEDIUM / LOW
    @NotBlank(message = "Priority is required")
    private String priority;

    // City / Area / Zone
    private String location;

    // 🔥 NEW (for sync + Bluetooth)
    private LocalDateTime createdAt;
}