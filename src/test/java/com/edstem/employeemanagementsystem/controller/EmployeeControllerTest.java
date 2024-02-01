package com.edstem.employeemanagementsystem.controller;


import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.model.Employee;
import com.edstem.employeemanagementsystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private EmployeeService employeeService;
    @Autowired ObjectMapper mapper = new ObjectMapper();


    @Test
    void testCreateEmployee() throws Exception {
        Long id = 1L;
        EmployeeRequest request = new EmployeeRequest("dev","dev@gmail.com","Dev");
        EmployeeResponse expectedResponse = new EmployeeResponse(1L, "dev","dev@gmail.com","Dev");
        when(employeeService.createEmployee(request)).thenReturn(expectedResponse);
        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void testGetEmployeeByDepartment() throws Exception {
        String department = "BDA";
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        employeeResponses.add(new EmployeeResponse(1L, "Devanand", "devanand@gmail.com", "BDA"));
        employeeResponses.add(new EmployeeResponse(2L, "sharuk", "sharuk@gmail.com", "BDA"));
        when(employeeService.getEmployeeByDepartment(department)).thenReturn(employeeResponses);

        mockMvc.perform(get("/employees/department/" + department))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(employeeResponses)));



    }

//    @Test
//
//    void testGetEmployee() throws Exception{
//        Long id = 1L;
//        EmployeeResponse employeeResponse = new EmployeeResponse(1L, "Devanand", "devanand@gmail.com", "BDA");



}


