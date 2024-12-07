package com.ismail.LaptopManagement.dto;

import com.ismail.LaptopManagement.model.IssueStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueStatusUpdateRequest {
    private IssueStatus issueStatus; // Ensure this matches the enum exactly
}

