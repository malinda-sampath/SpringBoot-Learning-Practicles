package com.microservice.employee_service.service;

import com.microservice.employee_service.dto.EmployeeDTO;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO);
}
