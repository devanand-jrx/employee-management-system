package com.edstem.employeemanagementsystem.repository;

import com.edstem.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findByDepartment(String department);

}
