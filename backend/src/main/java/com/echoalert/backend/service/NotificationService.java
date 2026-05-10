package com.echoalert.backend.service;

import com.echoalert.backend.dto.NotificationDTO;
import com.echoalert.backend.entity.NotificationLog;
import com.echoalert.backend.exception.ResourceNotFoundException;
import com.echoalert.backend.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    public NotificationService(NotificationRepository notificationRepository,
                               ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.modelMapper = modelMapper;
    }

    // Send Notification
    public NotificationLog sendNotification(NotificationDTO notificationDTO) {

        NotificationLog notification =
                modelMapper.map(notificationDTO, NotificationLog.class);

        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    // Get All Notification Logs
    public List<NotificationLog> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get Notification By Id
    public NotificationLog getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Notification not found with id: " + id));
    }

    // Delete Notification Log
    public void deleteNotification(Long id) {

        NotificationLog notification = getNotificationById(id);

        notificationRepository.delete(notification);
    }
}