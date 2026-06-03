package com.microservice.employee_service.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastname;
    private String email;
    private String departmentCode;
}
