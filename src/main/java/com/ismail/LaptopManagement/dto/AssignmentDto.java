package com.ismail.LaptopManagement.dto;

import lombok.Data;

@Data
public class AssignmentDto {
    private Long laptopId;
    private Long employeeId;
    private String assignedAt;
}

