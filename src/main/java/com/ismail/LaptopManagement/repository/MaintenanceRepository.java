package com.ismail.LaptopManagement.repository;


import com.ismail.LaptopManagement.model.Maintenance;
import com.ismail.LaptopManagement.model.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findByLaptopId(Long laptopId);

    List<Maintenance> findByStatus(MaintenanceStatus status);
}
