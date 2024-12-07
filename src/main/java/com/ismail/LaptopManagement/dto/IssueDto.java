package com.ismail.LaptopManagement.dto;

import lombok.Data;

@Data
public class IssueDto {
    private Long laptopId;
    private String description;
    private String priority;
    private String reportedBy;
}


