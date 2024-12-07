package com.ismail.LaptopManagement.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;

    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status; // Enum for status (PENDING, COMPLETED)

    private Double cost;
    private LocalDateTime loggedAt;
}

