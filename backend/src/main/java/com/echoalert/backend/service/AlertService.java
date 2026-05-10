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

    public AlertService(AlertRepository alertRepository,
                        ModelMapper modelMapper) {
        this.alertRepository = alertRepository;
        this.modelMapper = modelMapper;
    }

    // 🔥 UPDATED: Create Alert (UUID + duplicate protection)
    public Alert createAlert(AlertDTO alertDTO) {

        Alert alert = modelMapper.map(alertDTO, Alert.class);

        // ✅ UUID handling (important for mesh system)
        if (alertDTO.getUuid() != null && !alertDTO.getUuid().isBlank()) {

            // 🔁 Duplicate check (important for Bluetooth sync)
            Optional<Alert> existing = alertRepository.findByUuid(alertDTO.getUuid());
            if (existing.isPresent()) {
                return existing.get(); // already exists → return same
            }

            alert.setUuid(alertDTO.getUuid());

        } else {
            alert.setUuid(UUID.randomUUID().toString());
        }

        // ✅ Set default values
        alert.setCreatedAt(LocalDateTime.now());
        alert.setStatus("ACTIVE");

        return alertRepository.save(alert);
    }

    // ✅ Pagination (NO CHANGE)
    public Page<Alert> getAllAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findAll(pageable);
    }

    // ✅ Filter by priority
    public Page<Alert> getAlertsByPriority(String priority, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findByPriority(priority, pageable);
    }

    // 🔥 NEW: Sync API (for mobile)
    public Page<Alert> syncAlerts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return alertRepository.findAll(pageable);
    }

    // ✅ Get by ID
    public Alert getAlertById(Long id) {
        return alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with id: " + id));
    }

    // ✅ Delete
    public void deleteAlert(Long id) {
        Alert alert = getAlertById(id);
        alertRepository.delete(alert);
    }
}