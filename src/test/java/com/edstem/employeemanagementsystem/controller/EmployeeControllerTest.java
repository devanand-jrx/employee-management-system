package com.edstem.employeemanagementsystem.controller;


import com.edstem.employeemanagementsystem.contract.request.EmployeeRequest;
import com.edstem.employeemanagementsystem.contract.response.EmployeeResponse;
import com.edstem.employeemanagementsystem.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        EmployeeRequest request = new EmployeeRequest("name","dev@gmail.com","Dev");
        EmployeeResponse expectedResponse = new EmployeeResponse(1L, "dev","dev@gmail.com","Dev");
        when(employeeService.createEmployee(request)).thenReturn(expectedResponse);
        mockMvc.perform(
                post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                        .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

}


