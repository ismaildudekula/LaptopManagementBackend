package com.ismail.LaptopManagement.dto;

import lombok.Data;

@Data
public class MaintenanceDto {
    private Long laptopId;
    private String description;
    private String status;
    private Double cost;
    private String loggedAt;
}
