package com.ismail.LaptopManagement.repository;

import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findByStatus(LaptopStatus status);

    List<Laptop> findByBrandContainingIgnoreCase(String brand);

    List<Laptop> findByStatusAndBrandContainingIgnoreCase(LaptopStatus status, String brand);

}

