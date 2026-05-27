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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceIMPL implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final WebClient webClient;

    public EmployeeServiceIMPL(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, WebClient webClient) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.webClient = webClient;
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

        ResponseEntity<StandardResponse<DepartmentDTO>> responseEntity = webClient
                .get()
                .uri("http://localhost:8082/api/department/get-by-code?code="
                        + employee.getDepartmentCode())
                .retrieve()
                .toEntity(new ParameterizedTypeReference<
                        StandardResponse<DepartmentDTO>>() {})
                .block();

        //Synchronous & Asynchronous use
//        StandardResponse<DepartmentDTO> response =
//                webClient
//                        .get()
//                        .uri("http://localhost:8082/api/department/get-by-code?code="
//                                + employee.getDepartmentCode())
//                        .retrieve()
//                        .bodyToMono(new ParameterizedTypeReference<
//                                StandardResponse<DepartmentDTO>>() {})
//                        .block();

        assert Objects.requireNonNull(responseEntity).getBody() != null;
        DepartmentDTO departmentDTO = responseEntity.getBody().getData();

        return new ApiResponseDTO(employeeMapper.entityToDto(employee), departmentDTO);
    }
}
