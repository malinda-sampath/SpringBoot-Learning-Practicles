package com.microservice.department_service.service;

import com.microservice.department_service.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(DepartmentDTO departmentDTO);

    List<DepartmentDTO> getAllDepartment();
}
