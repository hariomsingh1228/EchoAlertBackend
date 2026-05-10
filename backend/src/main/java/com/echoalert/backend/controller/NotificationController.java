package com.echoalert.backend.controller;

import com.echoalert.backend.dto.NotificationDTO;
import com.echoalert.backend.entity.NotificationLog;
import com.echoalert.backend.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Send Notification
    @PostMapping("/send")
    public NotificationLog sendNotification(
            @Valid @RequestBody NotificationDTO notificationDTO) {

        return notificationService.sendNotification(notificationDTO);
    }

    // Get All Notification Logs
    @GetMapping
    public List<NotificationLog> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Get Notification By Id
    @GetMapping("/{id}")
    public NotificationLog getNotificationById(
            @PathVariable Long id) {

        return notificationService.getNotificationById(id);
    }

    // Delete Notification Log
    @DeleteMapping("/{id}")
    public String deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);
        return "Notification deleted successfully";
    }
}