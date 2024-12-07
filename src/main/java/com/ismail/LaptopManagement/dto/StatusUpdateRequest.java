package com.ismail.LaptopManagement.dto;

import com.ismail.LaptopManagement.model.RequestStatus;

public class StatusUpdateRequest {
    private RequestStatus newStatus;

    // Getters and setters
    public RequestStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(RequestStatus newStatus) {
        this.newStatus = newStatus;
    }
}

