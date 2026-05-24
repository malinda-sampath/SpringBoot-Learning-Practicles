package com.microservice.employee_service.service.impl;

import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.entity.Employee;
import com.microservice.employee_service.repository.EmployeeRepo;
import com.microservice.employee_service.service.EmployeeService;
import com.microservice.employee_service.utill.mappers.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceIMPL(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.dtoToEntity(employeeDTO);
        employeeRepo.save(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        return employeeMapper.entityListToDtoList(employees);
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepo.getReferenceById((long) id);
        return employeeMapper.entityToDto(employee);
    }
}
