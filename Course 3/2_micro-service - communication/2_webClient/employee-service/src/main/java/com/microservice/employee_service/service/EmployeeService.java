package com.microservice.employee_service.service;

import com.microservice.employee_service.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployee();

    EmployeeDTO getEmployeeById(int id);
}
