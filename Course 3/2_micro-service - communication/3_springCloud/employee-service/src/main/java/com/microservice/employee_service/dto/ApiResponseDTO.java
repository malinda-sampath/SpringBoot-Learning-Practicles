package com.microservice.employee_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO {
    private EmployeeDTO employeeDTO;
    private DepartmentDTO departmentDTO;
}