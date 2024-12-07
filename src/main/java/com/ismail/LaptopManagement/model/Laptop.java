package com.ismail.LaptopManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private LaptopStatus status; // Enum for status (AVAILABLE, ASSIGNED, MAINTENANCE)

    private LocalDate purchaseDate;
}
