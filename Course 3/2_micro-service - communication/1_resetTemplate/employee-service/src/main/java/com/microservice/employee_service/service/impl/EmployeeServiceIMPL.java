package com.microservice.employee_service.service.impl;

import com.microservice.employee_service.dto.ApiResponseDTO;
import com.microservice.employee_service.dto.DepartmentDTO;
import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.entity.Employee;
import com.microservice.employee_service.repository.EmployeeRepo;
import com.microservice.employee_service.service.EmployeeService;
import com.microservice.employee_service.utill.mappers.EmployeeMapper;
import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final RestTemplate restTemplate;

    public EmployeeServiceIMPL(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, RestTemplate restTemplate) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.restTemplate = restTemplate;
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

        ResponseEntity<StandardResponse<DepartmentDTO>> responseEntity = restTemplate.exchange(
                "http://localhost:8082/api/department/get-by-code?code=" + employee.getDepartmentCode(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
        });

        assert responseEntity.getBody() != null;
        DepartmentDTO departmentDTO = responseEntity.getBody().getData();

        return new ApiResponseDTO(employeeMapper.entityToDto(employee), departmentDTO);
    }
}
