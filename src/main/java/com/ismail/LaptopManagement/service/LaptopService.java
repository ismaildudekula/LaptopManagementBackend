package com.ismail.LaptopManagement.service;


import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import com.ismail.LaptopManagement.repository.LaptopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LaptopService {
    private final LaptopRepository laptopRepository;

    public Laptop addLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop getLaptopById(Long id) {
        return laptopRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Laptop not found with ID: " + id));
    }

    public Laptop updateLaptop(Long id, Laptop updatedLaptop) {
        Laptop existingLaptop = getLaptopById(id);
        existingLaptop.setBrand(updatedLaptop.getBrand());
        existingLaptop.setModel(updatedLaptop.getModel());
        existingLaptop.setSerialNumber(updatedLaptop.getSerialNumber());
        existingLaptop.setStatus(updatedLaptop.getStatus());
        existingLaptop.setPurchaseDate(updatedLaptop.getPurchaseDate());
        return laptopRepository.save(existingLaptop);
    }

    public void deleteLaptop(Long id) {
        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laptop not found with ID: " + id));
        laptopRepository.delete(laptop);
    }


    public List<Laptop> findByStatus(LaptopStatus status) {
        return laptopRepository.findByStatus(status);
    }

    public Laptop updateLaptopStatus(Long id, LaptopStatus newStatus) {
        Laptop laptop = getLaptopById(id);
        validateStatusTransition(laptop.getStatus(), newStatus);
        laptop.setStatus(newStatus);
        return laptopRepository.save(laptop);
    }

    private void validateStatusTransition(LaptopStatus currentStatus, LaptopStatus newStatus) {
        if (currentStatus == LaptopStatus.ASSIGNED && newStatus == LaptopStatus.AVAILABLE) {
            throw new IllegalStateException("Cannot change status directly from ASSIGNED to AVAILABLE. Must unassign first.");
        }
        if (currentStatus == LaptopStatus.MAINTENANCE && newStatus == LaptopStatus.ASSIGNED) {
            throw new IllegalStateException("Cannot assign laptop that is under maintenance.");
        }
    }
}

