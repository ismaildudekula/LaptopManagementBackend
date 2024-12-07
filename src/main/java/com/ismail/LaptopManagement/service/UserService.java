package com.ismail.LaptopManagement.service;


import com.ismail.LaptopManagement.model.Role;
import com.ismail.LaptopManagement.model.User;
import com.ismail.LaptopManagement.repository.RoleRepository;
import com.ismail.LaptopManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Get or create EMPLOYEE role
        Role employeeRole = roleRepository.findByName("EMPLOYEE")
                .orElseGet(() -> roleRepository.save(Role.builder().name("EMPLOYEE").build()));

        // Set default role as EMPLOYEE
        user.setRoles(Set.of(employeeRole));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
