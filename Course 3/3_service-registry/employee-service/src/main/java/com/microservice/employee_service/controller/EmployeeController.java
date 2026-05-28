package com.microservice.employee_service.controller;

import com.microservice.employee_service.dto.ApiResponseDTO;
import com.microservice.employee_service.dto.EmployeeDTO;
import com.microservice.employee_service.service.EmployeeService;
import com.microservice.employee_service.utill.response.ResponseBuilder;
import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "get-by-id",params = "id")
    public ResponseEntity<StandardResponse<ApiResponseDTO>> getEmployeeById(@RequestParam(value = "id") int id){
        return ResponseBuilder.ok(
                "Employee retrieved successfully",
                employeeService.getEmployeeById(id)
        );
    }

    @GetMapping(path = "get-all")
    public ResponseEntity<StandardResponse<List<EmployeeDTO>>> getAllEmployees(){
        return ResponseBuilder.ok(
                "Employees retrieved successfully",
                employeeService.getAllEmployee()
        );
    }
}
