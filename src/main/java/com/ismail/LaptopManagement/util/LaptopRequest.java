package com.ismail.LaptopManagement.util;

import com.ismail.LaptopManagement.model.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaptopRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String requestedBy;
    private String reason;
    private LocalDateTime requestedAt;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;

}

