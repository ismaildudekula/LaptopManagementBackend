package com.ismail.LaptopManagement.controller;

import com.ismail.LaptopManagement.dto.IssueStatusUpdateRequest;
import com.ismail.LaptopManagement.dto.StatusUpdateRequest;
import com.ismail.LaptopManagement.model.Issue;
import com.ismail.LaptopManagement.model.IssueStatus;
import com.ismail.LaptopManagement.model.PriorityLevel;
import com.ismail.LaptopManagement.service.IssueService;
import com.ismail.LaptopManagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
    private final IssueService issueService;

    @GetMapping
    public ResponseEntity<List<Issue>> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @PostMapping
    public ResponseEntity<Issue> reportIssue(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.reportIssue(issue));
    }

    @PutMapping("/update-status/{issueId}")
    public ResponseEntity<ApiResponse<Void>> updateIssueStatus(@PathVariable Long issueId, @RequestBody IssueStatusUpdateRequest request) {
        if (request.getIssueStatus() == null) {
            return ResponseEntity.badRequest().body(ApiResponse.<Void>builder()
                    .success(false)
                    .message("Issue status is required").build());
        }
        issueService.updateIssueStatus(issueId, request.getIssueStatus());
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("Issue status successfully updated").build());
    }


    @GetMapping("/laptop/{laptopId}")
    public ResponseEntity<List<Issue>> getIssuesByLaptop(@PathVariable Long laptopId) {
        return ResponseEntity.ok(issueService.getIssuesByLaptop(laptopId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Issue>> getIssuesByStatus(@PathVariable IssueStatus status) {
        return ResponseEntity.ok(issueService.getIssuesByStatus(status));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Issue>> getIssuesByPriority(@PathVariable PriorityLevel priority) {
        return ResponseEntity.ok(issueService.getIssuesByPriority(priority));
    }
}

