package com.ismail.LaptopManagement.repository;

import com.ismail.LaptopManagement.model.RequestStatus;
import com.ismail.LaptopManagement.util.LaptopRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRequestRepository extends JpaRepository<LaptopRequest, Long> {
    List<LaptopRequest> findByRequestedBy(String username);
    List<LaptopRequest> findByStatus(RequestStatus status);
}

