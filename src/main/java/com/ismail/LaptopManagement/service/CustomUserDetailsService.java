package com.ismail.LaptopManagement.service;

import com.ismail.LaptopManagement.model.Employee;
import com.ismail.LaptopManagement.model.User;
import com.ismail.LaptopManagement.repository.EmployeeRepository;
import com.ismail.LaptopManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(employee.getEmail())
                .password(employee.getPassword())
                .authorities(employee.getRoles().stream()
                        .map(role -> "ROLE_" + role.getName().toUpperCase())
                        .collect(Collectors.toList())
                        .toArray(String[]::new))
                .build();
    }
}
