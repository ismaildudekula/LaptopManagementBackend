package com.ismail.LaptopManagement.service;


import com.ismail.LaptopManagement.dto.IssueDto;
import com.ismail.LaptopManagement.dto.LaptopDto;
import com.ismail.LaptopManagement.model.Issue;
import com.ismail.LaptopManagement.model.IssueStatus;
import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.PriorityLevel;
import com.ismail.LaptopManagement.repository.IssueRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final LaptopService laptopService;
    private final AssignmentService assignmentService;

    public Issue reportIssue(Issue issue) {
        Laptop laptop = laptopService.getLaptopById(issue.getLaptop().getId());
        issue.setLaptop(laptop);
        issue.setReportedAt(LocalDateTime.now());
        issue.setStatus(IssueStatus.OPEN);
        return issueRepository.save(issue);
    }

    public List<Issue> getIssuesByLaptop(Long laptopId) {
        return issueRepository.findByLaptopId(laptopId);
    }

    public List<Issue> getIssuesByStatus(IssueStatus status) {
        return issueRepository.findByStatus(status);
    }

    public List<Issue> getIssuesByPriority(PriorityLevel priority) {
        return issueRepository.findByPriority(priority);
    }

    public Issue reportIssue(IssueDto issueDto) {
        // Check if laptop is assigned to the reporting employee
        List<LaptopDto> assignedLaptops = assignmentService.getLaptopsByEmployeeUsername(issueDto.getReportedBy());
        boolean isLaptopAssigned = assignedLaptops.stream()
                .anyMatch(laptop -> laptop.getId().equals(issueDto.getLaptopId()));

        if (!isLaptopAssigned) {
            throw new RuntimeException("You can only report issues for laptops assigned to you");
        }

        // Rest of the issue creation logic
        Laptop laptop = laptopService.getLaptopById(issueDto.getLaptopId());
        Issue issue = Issue.builder()
                .laptop(laptop)
                .description(issueDto.getDescription())
                .priority(PriorityLevel.valueOf(issueDto.getPriority()))
                .status(IssueStatus.OPEN)
                .reportedBy(issueDto.getReportedBy())
                .reportedAt(LocalDateTime.now())
                .build();

        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssuesByReportedBy(String username) {
        return issueRepository.findByReportedBy(username);
    }

    public void updateIssueStatus(Long issueId, IssueStatus issueStatus) {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if (issue.isPresent()) {
            if (issueStatus != null) {
                issue.get().setStatus(issueStatus);
                issueRepository.save(issue.get());
            } else {
                throw new IllegalArgumentException("Issue status cannot be null");
            }
        } else {
            throw new EntityNotFoundException("Issue not found with ID: " + issueId);
        }
    }

}

