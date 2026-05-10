package com.echoalert.backend.service;

import com.echoalert.backend.dto.UserDTO;
import com.echoalert.backend.entity.User;
import com.echoalert.backend.exception.ResourceNotFoundException;
import com.echoalert.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Get All Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Get User By Id
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id));
    }

    // ✅ Update User
    public User updateUser(Long id, UserDTO userDTO) {

        User user = getUserById(id);

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setLocation(userDTO.getLocation());

        // 🔥 ENUM direct set (DTO already enum hai)
        user.setRole(userDTO.getRole());

        user.setEnabled(userDTO.isEnabled());

        // ✅ Password update only if provided
        if (userDTO.getPassword() != null &&
                !userDTO.getPassword().trim().isEmpty()) {

            user.setPassword(
                    passwordEncoder.encode(userDTO.getPassword())
            );
        }

        return userRepository.save(user);
    }

    // ✅ Delete User
    public void deleteUser(Long id) {

        User user = getUserById(id);

        userRepository.delete(user);
    }
}