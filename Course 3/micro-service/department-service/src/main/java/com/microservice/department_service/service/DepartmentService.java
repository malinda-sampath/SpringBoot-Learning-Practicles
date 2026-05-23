package com.microservice.department_service.service;

import com.microservice.department_service.dto.DepartmentDTO;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);
}
