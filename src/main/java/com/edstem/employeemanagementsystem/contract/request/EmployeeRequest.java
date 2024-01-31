package com.edstem.employeemanagementsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "name cannot be empty")
    private String name;

    @NotBlank(message = "email is required")
    private String email;

    private String department;
}
