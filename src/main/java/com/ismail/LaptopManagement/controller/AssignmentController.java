package com.ismail.LaptopManagement.controller;


import com.ismail.LaptopManagement.model.Assignment;
import com.ismail.LaptopManagement.service.AssignmentService;
import com.ismail.LaptopManagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @PostMapping("/assign/{laptopId}/{employeeId}")
    public ResponseEntity<Assignment> assignLaptop(
            @PathVariable Long laptopId,
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(assignmentService.assignLaptop(laptopId, employeeId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByEmployee(employeeId));
    }

    @PutMapping("/unassign/{assignmentId}")
    public ResponseEntity<ApiResponse<Void>> unassignLaptop(@PathVariable Long assignmentId) {
        assignmentService.unassignLaptop(assignmentId);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Laptop unassigned successfully for assignment ID: " + assignmentId)
                .build());
    }

}
