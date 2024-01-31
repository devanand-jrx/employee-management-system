package com.edstem.employeemanagementsystem.service;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.exception.EmployeeNotFoundException;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeResponse createEmployee(EmployeeRequest request){
        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(request.getDepartment())
                .build();

        employee = employeeRepository.save(employee);
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    public EmployeeResponse getEmployee(Long id){
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("No Employee found with the given id."));
        return modelMapper.map(employee , EmployeeResponse.class);

    }

    public List<EmployeeResponse> getEmployeeByDepartment(String department){
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employees.stream()
                .map(employee -> modelMapper.map(employee , EmployeeResponse.class))
                .collect(Collectors.toList());
    }
}
