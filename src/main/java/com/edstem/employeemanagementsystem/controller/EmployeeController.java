package com.edstem.employeemanagementsystem.controller;

import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.service.EmployeeService;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired private EmployeeService employeeService;

    @PostMapping
    public @ResponseBody EmployeeResponse createEmployee( @Valid
            @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @GetMapping("/{id}")
    public @ResponseBody EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @GetMapping()
    public @ResponseBody List<EmployeeResponse> getEmployeeByDepartment(
            @RequestParam String department) {
        return employeeService.getEmployeeByDepartment(department);
    }
}
