package com.ismail.LaptopManagement.controller;

import com.ismail.LaptopManagement.model.Maintenance;
import com.ismail.LaptopManagement.model.MaintenanceStatus;
import com.ismail.LaptopManagement.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @PostMapping
    public ResponseEntity<Maintenance> logMaintenance(@RequestBody Maintenance maintenance) {
        return ResponseEntity.ok(maintenanceService.logMaintenance(maintenance));
    }

    @GetMapping("/laptop/{laptopId}")
    public ResponseEntity<List<Maintenance>> getMaintenanceHistoryByLaptop(@PathVariable Long laptopId) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceHistoryByLaptop(laptopId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Maintenance>> getMaintenanceByStatus(@PathVariable MaintenanceStatus status) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceByStatus(status));
    }
}

