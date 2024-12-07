package com.ismail.LaptopManagement.service;


import com.ismail.LaptopManagement.model.Employee;
import com.ismail.LaptopManagement.model.Role;
import com.ismail.LaptopManagement.repository.EmployeeRepository;
import com.ismail.LaptopManagement.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee) {
        if (employee.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new RuntimeException("Email " + employee.getEmail() + " is already registered");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Role employeeRole = roleRepository.findByName("EMPLOYEE")
                .orElseGet(() -> roleRepository.save(Role.builder().name("EMPLOYEE").build()));
        employee.setRoles(Set.of(employeeRole));
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id);
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
    }
}

