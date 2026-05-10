package com.echoalert.backend.mapper;

import com.echoalert.backend.dto.UserDTO;
import com.echoalert.backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    // Entity -> DTO
    public UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        UserDTO dto = new UserDTO();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setLocation(user.getLocation());
        dto.setRole(user.getRole());
        dto.setEnabled(user.isEnabled());

        // password intentionally not exposed
        dto.setPassword(null);

        return dto;
    }

    // DTO -> Entity
    public User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setLocation(dto.getLocation());
        user.setRole(dto.getRole());
        user.setEnabled(dto.isEnabled());

        // password set separately in service after encoding
        return user;
    }

    // Update Existing Entity from DTO
    public void updateEntity(UserDTO dto, User user) {

        if (dto == null || user == null) {
            return;
        }

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setLocation(dto.getLocation());
        user.setRole(dto.getRole());
        user.setEnabled(dto.isEnabled());
    }
}