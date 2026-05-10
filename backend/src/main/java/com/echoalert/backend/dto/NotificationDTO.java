package com.echoalert.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationDTO {

    // Optional Related Alert
    private Long alertId;

    // Optional Receiver User
    private Long userId;

    // EMAIL / SMS / PUSH / IN_APP
    @NotBlank(message = "Notification type is required")
    private String type;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;

    // email / mobile / deviceToken
    private String recipient;
}