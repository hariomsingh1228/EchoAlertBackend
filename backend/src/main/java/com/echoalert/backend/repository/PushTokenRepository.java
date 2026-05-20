package com.echoalert.backend.repository;

import com.echoalert.backend.entity.PushToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushTokenRepository extends JpaRepository<PushToken, Long> {
}