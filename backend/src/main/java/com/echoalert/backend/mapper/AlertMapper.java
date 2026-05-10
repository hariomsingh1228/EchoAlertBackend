package com.echoalert.backend.mapper;

import com.echoalert.backend.dto.AlertDTO;
import com.echoalert.backend.entity.Alert;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AlertMapper {

    // DTO -> Entity
    public Alert toEntity(AlertDTO dto) {

        if (dto == null) {
            return null;
        }

        Alert alert = new Alert();

        alert.setTitle(dto.getTitle());
        alert.setMessage(dto.getMessage());
        alert.setPriority(dto.getPriority());   // String field
        alert.setLocation(dto.getLocation());
        alert.setStatus("ACTIVE");
        alert.setCreatedAt(LocalDateTime.now());

        return alert;
    }

    // Entity -> DTO
    public AlertDTO toDTO(Alert alert) {

        if (alert == null) {
            return null;
        }

        AlertDTO dto = new AlertDTO();

        dto.setTitle(alert.getTitle());
        dto.setMessage(alert.getMessage());
        dto.setPriority(alert.getPriority());   // String field
        dto.setLocation(alert.getLocation());

        return dto;
    }
}