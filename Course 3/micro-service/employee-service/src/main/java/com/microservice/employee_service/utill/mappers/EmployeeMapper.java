package com.microservice.employee_service.utill.mappers;

import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToEntity(EmployeeDTO employeeDTO);
}
