package com.ismail.LaptopManagement.service;


import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import com.ismail.LaptopManagement.model.Maintenance;
import com.ismail.LaptopManagement.model.MaintenanceStatus;
import com.ismail.LaptopManagement.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final LaptopService laptopService;

    public Maintenance logMaintenance(Maintenance maintenance) {
        Laptop laptop = laptopService.getLaptopById(maintenance.getLaptop().getId());
        maintenance.setLaptop(laptop);
        maintenance.setLoggedAt(LocalDateTime.now());

        // Update laptop status to MAINTENANCE
        laptop.setStatus(LaptopStatus.MAINTENANCE);
        laptopService.updateLaptop(laptop.getId(), laptop);

        return maintenanceRepository.save(maintenance);
    }

    public List<Maintenance> getMaintenanceHistoryByLaptop(Long laptopId) {
        return maintenanceRepository.findByLaptopId(laptopId);
    }

    public List<Maintenance> getMaintenanceByStatus(MaintenanceStatus status) {
        return maintenanceRepository.findByStatus(status);
    }
}
