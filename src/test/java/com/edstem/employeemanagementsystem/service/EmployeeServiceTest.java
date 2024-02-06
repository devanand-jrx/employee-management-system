package com.edstem.employeemanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class EmployeeServiceTest {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        modelMapper = new ModelMapper();
        employeeService = new EmployeeService(employeeRepository, modelMapper);
    }

    @Test
    public void testAddEmployee() {
        EmployeeRequest employeeRequest =
                new EmployeeRequest("devanand", "devanand@gmail.com", "BDA");
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeResponse actualResponse = employeeService.createEmployee(employeeRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetEmployee() {
        Long id = 1L;
        Employee employee = new Employee(id, "Devanand", "devanand@gmail.com", "BDA");
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        EmployeeResponse actualResponse = employeeService.getEmployee(id);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetEmployeeByDepartment() {
        String department = "BDA";

        Employee employee1 = new Employee(1L, "Devanand", "devanand@gmail.com", "BDA");
        Employee employee2 = new Employee(2L, "Sharok", "sharok@gmail.com", "BDA");
        List<Employee> employees = Arrays.asList(employee1, employee2);
        List<EmployeeResponse> expectedResponse =
                employees.stream()
                        .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
                        .collect(Collectors.toList());
        when(employeeRepository.findByDepartment(department)).thenReturn(employees);
        List<EmployeeResponse> actualResponse = employeeService.getEmployeeByDepartment(department);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetEmployeeThrowsException() {
        Long id = 1L;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(id));
    }
}
