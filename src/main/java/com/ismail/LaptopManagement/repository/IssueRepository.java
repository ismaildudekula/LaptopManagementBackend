package com.ismail.LaptopManagement.repository;


import com.ismail.LaptopManagement.model.Issue;
import com.ismail.LaptopManagement.model.IssueStatus;
import com.ismail.LaptopManagement.model.PriorityLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByLaptopId(Long laptopId);

    List<Issue> findByStatus(IssueStatus status);

    List<Issue> findByPriority(PriorityLevel priority);

    List<Issue> findByReportedByAndStatus(String reportedBy, IssueStatus status);

    List<Issue> findByReportedBy(String username);
}

