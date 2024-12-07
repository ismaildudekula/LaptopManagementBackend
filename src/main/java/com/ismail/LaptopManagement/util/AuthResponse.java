package com.ismail.LaptopManagement.util;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String email;
    private List<String> roles;
}


