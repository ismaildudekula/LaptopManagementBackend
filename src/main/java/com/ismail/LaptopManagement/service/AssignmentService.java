package com.ismail.LaptopManagement.service;

import com.ismail.LaptopManagement.dto.LaptopDto;
import com.ismail.LaptopManagement.model.Assignment;
import com.ismail.LaptopManagement.model.Employee;
import com.ismail.LaptopManagement.model.Laptop;
import com.ismail.LaptopManagement.model.LaptopStatus;
import com.ismail.LaptopManagement.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final LaptopService laptopService;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public Assignment assignLaptop(Long laptopId, Long employeeId) {
        Laptop laptop = laptopService.getLaptopById(laptopId);

        if (laptop.getStatus() != LaptopStatus.AVAILABLE) {
            throw new RuntimeException("Laptop is not available for assignment");
        }

        Employee employee = employeeService.getEmployeeById(employeeId);

        laptop.setStatus(LaptopStatus.ASSIGNED);
        laptopService.updateLaptop(laptopId, laptop);

        Assignment assignment = Assignment.builder()
                .laptop(laptop)
                .employee(employee)
                .assignedAt(LocalDateTime.now())
                .build();

        return assignmentRepository.save(assignment);
    }


    public List<Assignment> getAssignmentsByEmployee(Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return assignmentRepository.findByEmployeeAndReturnedAtIsNull(employee);
    }


    public void unassignLaptop(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + assignmentId));

        // Update laptop status to AVAILABLE
        Laptop laptop = assignment.getLaptop();
        laptop.setStatus(LaptopStatus.AVAILABLE);
        laptopService.updateLaptop(laptop.getId(), laptop);

        // Set return time
        assignment.setReturnedAt(LocalDateTime.now());
        assignmentRepository.save(assignment);
    }


    public List<LaptopDto> getLaptopsByEmployeeUsername(String username) {
        Employee employee = employeeService.findByEmail(username);
        List<Assignment> assignments = getAssignmentsByEmployee(employee.getId());
        return assignments.stream()
                .map(assignment -> modelMapper.map(assignment.getLaptop(), LaptopDto.class))
                .collect(Collectors.toList());
    }
}

