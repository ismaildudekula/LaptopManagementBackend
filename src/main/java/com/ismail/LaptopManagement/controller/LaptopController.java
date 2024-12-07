package com.ismail.LaptopManagement.controller;


import com.ismail.LaptopManagement.dto.StatusUpdateRequest;
import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import com.ismail.LaptopManagement.model.RequestStatus;
import com.ismail.LaptopManagement.service.LaptopRequestService;
import com.ismail.LaptopManagement.service.LaptopService;
import com.ismail.LaptopManagement.util.ApiResponse;
import com.ismail.LaptopManagement.util.LaptopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
@RequiredArgsConstructor
public class LaptopController {
    private final LaptopService laptopService;
    private final LaptopRequestService laptopRequestService;

    @PostMapping
    public ResponseEntity<Laptop> addLaptop(@RequestBody Laptop laptop) {
        return ResponseEntity.ok(laptopService.addLaptop(laptop));
    }

    @GetMapping
    public ResponseEntity<List<Laptop>> getAllLaptops() {
        return ResponseEntity.ok(laptopService.getAllLaptops());
    }

    @GetMapping("/laptop-requests")
    public ResponseEntity<List<LaptopRequest>> getLaptopRequests() {
        return ResponseEntity.ok(laptopRequestService.getLaptopRequests());
    }

    @PutMapping("/laptop-requests/change-status/{id}")
    public ResponseEntity<ApiResponse<Void>> changeRequestLaptopStatus(@PathVariable  Long id, @RequestBody StatusUpdateRequest statusUpdateRequest) {
        laptopRequestService.changeLaptopRequestStatus(id, statusUpdateRequest.getNewStatus());
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Laptop request status successfully updated").build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable Long id) {
        return ResponseEntity.ok(laptopService.getLaptopById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop) {
        return ResponseEntity.ok(laptopService.updateLaptop(id, laptop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Laptop with ID: " + id + " deleted successfully")
                .build());
    }


    @GetMapping("/status/{status}")
    public ResponseEntity<List<Laptop>> findByStatus(@PathVariable LaptopStatus status) {
        return ResponseEntity.ok(laptopService.findByStatus(status));
    }
}

