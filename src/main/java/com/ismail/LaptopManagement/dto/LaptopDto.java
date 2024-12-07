package com.ismail.LaptopManagement.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LaptopDto {
    private Long id;
    private String brand;
    private String model;
    private String serialNumber;
    private String status;
    private LocalDate purchaseDate;
}

