package com.ismail.LaptopManagement.service;

import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import com.ismail.LaptopManagement.model.RequestStatus;
import com.ismail.LaptopManagement.repository.LaptopRequestRepository;
import com.ismail.LaptopManagement.util.LaptopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaptopRequestService {
    private final LaptopRequestRepository requestRepository;

    public LaptopRequest createRequest(LaptopRequest request) {
        request.setRequestedAt(LocalDateTime.now());
        request.setStatus(RequestStatus.PENDING);
        return requestRepository.save(request);
    }

    public List<LaptopRequest> getLaptopRequests() {
        return requestRepository.findAll();
    }

    public void changeLaptopRequestStatus(Long laptopRequestId, RequestStatus newStatus) {
        Optional<LaptopRequest> laptopRequest = requestRepository.findById(laptopRequestId);
        laptopRequest.get().setStatus(newStatus);
        requestRepository.save(laptopRequest.get());
    }

    public List<LaptopRequest> getRequestsByRequestedBy(String username) {
        return requestRepository.findByRequestedBy(username);
    }
}

