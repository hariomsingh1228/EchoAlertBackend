package com.echoalert.backend.controller;

import com.echoalert.backend.dto.AlertDTO;
import com.echoalert.backend.entity.Alert;
import com.echoalert.backend.service.AlertService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin("*")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    // ✅ Create New Alert
    @PostMapping
    public Alert createAlert(@Valid @RequestBody AlertDTO alertDTO) {
        return alertService.createAlert(alertDTO);
    }

    // ✅ Get All Alerts (Pagination)
    @GetMapping
    public Page<Alert> getAllAlerts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return alertService.getAllAlerts(page, size);
    }

    // ✅ Filter by priority
    @GetMapping("/filter")
    public Page<Alert> getAlertsByPriority(
            @RequestParam String priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return alertService.getAlertsByPriority(priority, page, size);
    }

    // 🔥 NEW: Sync API (mobile + Bluetooth use)
    @GetMapping("/sync")
    public Page<Alert> syncAlerts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return alertService.syncAlerts(page, size);
    }

    // 🔥 NEW: Create alert from mobile (Bluetooth received data)
    @PostMapping("/sync")
    public Alert syncCreateAlert(@RequestBody AlertDTO alertDTO) {
        return alertService.createAlert(alertDTO);
    }

    // ✅ Get Alert By Id
    @GetMapping("/{id}")
    public Alert getAlertById(@PathVariable Long id) {
        return alertService.getAlertById(id);
    }

    // ✅ Delete Alert
    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return "Alert deleted successfully";
    }
}