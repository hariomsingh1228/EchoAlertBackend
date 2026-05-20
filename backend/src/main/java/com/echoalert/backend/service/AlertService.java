package com.echoalert.backend.service;

import com.echoalert.backend.dto.AlertDTO;
import com.echoalert.backend.entity.Alert;
import com.echoalert.backend.exception.ResourceNotFoundException;
import com.echoalert.backend.repository.AlertRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AlertService {

    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    public AlertService(AlertRepository alertRepository,
                        ModelMapper modelMapper,
                        NotificationService notificationService) {
        this.alertRepository = alertRepository;
        this.modelMapper = modelMapper;
        this.notificationService = notificationService;
    }

    // Create Alert + Send Push Notification Automatically
    public Alert createAlert(AlertDTO alertDTO) {

        Alert alert = modelMapper.map(alertDTO, Alert.class);

        // UUID handling for Bluetooth mesh sync
        if (alertDTO.getUuid() != null && !alertDTO.getUuid().isBlank()) {

            Optional<Alert> existing = alertRepository.findByUuid(alertDTO.getUuid());

            if (existing.isPresent()) {
                return existing.get();
            }

            alert.setUuid(alertDTO.getUuid());

        } else {
            alert.setUuid(UUID.randomUUID().toString());
        }

        alert.setCreatedAt(LocalDateTime.now());
        alert.setStatus("ACTIVE");
        alert.setSent(false);

        // Save alert first
        Alert savedAlert = alertRepository.save(alert);

        // Send notification automatically
        boolean notificationSent =
                notificationService.sendAlertNotification(savedAlert);

        // If notification sent successfully, mark sent = true
        if (notificationSent) {
            savedAlert.setSent(true);
            savedAlert = alertRepository.save(savedAlert);
            System.out.println("✅ Alert marked as sent: " + savedAlert.getId());
        } else {
            System.out.println("⚠️ Alert saved but notification not sent: " + savedAlert.getId());
        }

        return savedAlert;
    }

    public Page<Alert> getAllAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findAll(pageable);
    }

    public Page<Alert> getAlertsByPriority(String priority, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findByPriority(priority, pageable);
    }

    public Page<Alert> syncAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findAll(pageable);
    }

    public Alert getAlertById(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with id: " + id));
    }

    public void deleteAlert(Long id) {
        Alert alert = getAlertById(id);
        alertRepository.delete(alert);
    }
}