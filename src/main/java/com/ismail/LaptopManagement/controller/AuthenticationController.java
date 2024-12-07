package com.ismail.LaptopManagement.controller;


import com.ismail.LaptopManagement.config.JwtTokenProvider;
import com.ismail.LaptopManagement.model.Employee;
import com.ismail.LaptopManagement.model.User;
import com.ismail.LaptopManagement.service.EmployeeService;
import com.ismail.LaptopManagement.service.UserService;
import com.ismail.LaptopManagement.util.AuthRequest;
import com.ismail.LaptopManagement.util.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final EmployeeService employeeService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.registerEmployee(employee));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String jwt = tokenProvider.generateToken(authentication.getName());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwt)
                .email(userDetails.getUsername())
                .roles(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .build());
    }
}

