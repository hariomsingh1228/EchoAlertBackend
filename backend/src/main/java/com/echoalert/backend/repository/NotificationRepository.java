package com.echoalert.backend.repository;

import com.echoalert.backend.entity.NotificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository
        extends JpaRepository<NotificationLog, Long> {

    // Find all notifications by userId
    List<NotificationLog> findByUserId(Long userId);

    // Find all notifications by alertId
    List<NotificationLog> findByAlertId(Long alertId);

    // Find all notifications by status
    List<NotificationLog> findByStatus(String status);

    // Find all notifications by type
    List<NotificationLog> findByType(String type);
}