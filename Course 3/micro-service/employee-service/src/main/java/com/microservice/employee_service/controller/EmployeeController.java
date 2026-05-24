package com.microservice.employee_service.controller;

import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.service.EmployeeService;
import com.microservice.employee_service.utill.response.ResponseBuilder;
import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "save")
    public ResponseEntity<StandardResponse<Void>> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return ResponseBuilder.created(
                "Employee saved successfully",
                null
        );
    }
}
