package com.microservice.employee_service.service.impl;

import com.microservice.employee_service.dto.ApiResponseDTO;
import com.microservice.employee_service.dto.DepartmentDTO;
import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.entity.Employee;
import com.microservice.employee_service.repository.EmployeeRepo;
import com.microservice.employee_service.service.APIClient;
import com.microservice.employee_service.service.EmployeeService;
import com.microservice.employee_service.utill.mappers.EmployeeMapper;
import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final APIClient apiClient;

    public EmployeeServiceIMPL(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, APIClient apiClient) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.apiClient = apiClient;
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
    public ApiResponseDTO getEmployeeById(int id) {
        Employee employee = employeeRepo.getReferenceById((long) id);

        ResponseEntity<StandardResponse<DepartmentDTO>> responseEntity = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        assert responseEntity.getBody() != null;
        DepartmentDTO departmentDTO = responseEntity.getBody().getData();

        return new ApiResponseDTO(employeeMapper.entityToDto(employee), departmentDTO);
    }
}
