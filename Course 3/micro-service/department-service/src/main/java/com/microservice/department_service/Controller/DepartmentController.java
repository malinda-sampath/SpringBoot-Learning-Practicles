package com.microservice.department_service.Controller;

import com.microservice.department_service.dto.DepartmentDTO;
import com.microservice.department_service.service.DepartmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentDTO saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return departmentService.saveDepartment(departmentDTO);
    }
}
