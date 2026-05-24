package com.microservice.department_service.utill.mappers;

import com.microservice.department_service.dto.DepartmentDTO;
import com.microservice.department_service.entity.Department;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department dtoToEntity(DepartmentDTO departmentDTO);

    DepartmentDTO entityToDto(Department department);

    List<DepartmentDTO> entityListToDtoList(List<Department> departments);
}
