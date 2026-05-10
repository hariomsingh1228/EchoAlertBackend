package com.echoalert.backend.repository;

import com.echoalert.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);

    // Find users by role
    java.util.List<User> findByRole(String role);

    // Find users by enabled status
    java.util.List<User> findByEnabled(boolean enabled);
}