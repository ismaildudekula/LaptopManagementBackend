package com.ismail.LaptopManagement.controller;

import com.ismail.LaptopManagement.dto.IssueDto;
import com.ismail.LaptopManagement.dto.LaptopDto;
import com.ismail.LaptopManagement.model.Issue;
import com.ismail.LaptopManagement.service.AssignmentService;
import com.ismail.LaptopManagement.service.IssueService;
import com.ismail.LaptopManagement.service.LaptopRequestService;
import com.ismail.LaptopManagement.util.LaptopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeePortalController {
    private final AssignmentService assignmentService;
    private final IssueService issueService;
    private final LaptopRequestService laptopRequestService;

    @GetMapping("/my-laptops")
    public ResponseEntity<List<LaptopDto>> getMyLaptops(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(assignmentService.getLaptopsByEmployeeUsername(username));
    }

    @PostMapping("/request-laptop")
    public ResponseEntity<LaptopRequest> requestNewLaptop(@RequestBody LaptopRequest request,
                                                          @AuthenticationPrincipal UserDetails userDetails) {
        request.setRequestedBy(userDetails.getUsername());
        return ResponseEntity.ok(laptopRequestService.createRequest(request));
    }

    @PostMapping("/report-issue")
    public ResponseEntity<Issue> reportIssue(@RequestBody IssueDto issueDto,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        issueDto.setReportedBy(userDetails.getUsername());
        return ResponseEntity.ok(issueService.reportIssue(issueDto));
    }

    @GetMapping("/my-laptop-requests")
    public ResponseEntity<List<LaptopRequest>> getMyLaptopRequests(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(laptopRequestService.getRequestsByRequestedBy(username));
    }

    @GetMapping("/my-issues")
    public ResponseEntity<List<Issue>> getMyIssues(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(issueService.getIssuesByReportedBy(username));
    }
}

