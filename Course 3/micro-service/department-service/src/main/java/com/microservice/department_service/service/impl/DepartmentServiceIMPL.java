package com.microservice.department_service.service.impl;

import com.microservice.department_service.dto.DepartmentDTO;
import com.microservice.department_service.entity.Department;
import com.microservice.department_service.repository.DepartmentRepo;
import com.microservice.department_service.service.DepartmentService;
import com.microservice.department_service.utill.mappers.DepartmentMapper;

public class DepartmentServiceIMPL implements DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceIMPL(DepartmentMapper departmentMapper, DepartmentRepo departmentRepo) {
        this.departmentMapper = departmentMapper;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.dtoToEntity(departmentDTO);
        return departmentMapper.entityToDto(departmentRepo.save(department));
    }
}
