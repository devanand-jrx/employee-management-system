package com.edstem.employeemanagementsystem.service;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    private EmployeeService employeeService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        employeeRepository = Mockito.mock(EmployeeRepository.class);
        modelMapper = new ModelMapper();
        employeeService = new EmployeeService(employeeRepository, modelMapper);

    }

    @Test
    public void testAddEmployee(){
        EmployeeRequest employeeRequest = new EmployeeRequest ("devanand", "devanand@gmail.com","BDA");
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        EmployeeResponse expectedResponse = modelMapper.map(employee, EmployeeResponse.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        when(modelMapper.map(employee, EmployeeResponse.class)).thenReturn(expectedResponse);
        EmployeeResponse actualResponse = employeeService.createEmployee(employeeRequest);
        assertEquals(expectedResponse, actualResponse);
    }


}
