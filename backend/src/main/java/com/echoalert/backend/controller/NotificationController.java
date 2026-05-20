package com.echoalert.backend.controller;

import com.echoalert.backend.entity.Alert;
import com.echoalert.backend.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // ✅ Test route
    @GetMapping("/test")
    public String testNotificationController() {
        return "Notification controller is working. Notifications are now sent automatically when an alert is created.";
    }

    // ✅ Optional manual test using Alert body
    @PostMapping("/send-alert")
    public String sendAlertNotification(@RequestBody Alert alert) {
        boolean sent = notificationService.sendAlertNotification(alert);

        if (sent) {
            return "Notification sent successfully";
        }

        return "Notification failed or no valid push tokens found";
    }
}