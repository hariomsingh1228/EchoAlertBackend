package com.echoalert.backend.repository;

import com.echoalert.backend.entity.Alert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    // ✅ Filter by priority
    Page<Alert> findByPriority(String priority, Pageable pageable);

    // 🔥 NEW: Find by UUID (IMPORTANT for mesh system)
    Optional<Alert> findByUuid(String uuid);
}