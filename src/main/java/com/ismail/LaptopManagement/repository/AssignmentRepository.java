package com.ismail.LaptopManagement.repository;


import com.ismail.LaptopManagement.model.Assignment;
import com.ismail.LaptopManagement.model.Employee;
import com.ismail.LaptopManagement.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByEmployee(Employee employee);
    List<Assignment> findByLaptop(Laptop laptop);
    List<Assignment> findByAssignedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Assignment> findByEmployeeAndReturnedAtIsNull(Employee employee);

}


