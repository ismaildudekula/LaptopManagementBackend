package com.ismail.LaptopManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "laptop_id", nullable = false)
    private Laptop laptop;

    private String description;

    @Enumerated(EnumType.STRING)
    private PriorityLevel priority; // Enum for priority (LOW, MEDIUM, HIGH)

    @Enumerated(EnumType.STRING)
    private IssueStatus status; // Enum for issue status (OPEN, RESOLVED, CLOSED)

    private String reportedBy;
    private LocalDateTime reportedAt;
}

