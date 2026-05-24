package com.microservice.department_service.Controller;

import com.microservice.department_service.dto.DepartmentDTO;
import com.microservice.department_service.entity.Department;
import com.microservice.department_service.service.DepartmentService;
import com.microservice.department_service.utill.response.ResponseBuilder;
import com.microservice.department_service.utill.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse<DepartmentDTO>> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.saveDepartment(departmentDTO);
        return ResponseBuilder.created(
                "Department saved successfully",
                null
        );
    }

    @GetMapping(path = "get-by-code",params = "code")
    public ResponseEntity<StandardResponse<DepartmentDTO>> getDepartmentByCode(@RequestParam(value = "code") String code){
        return ResponseBuilder.ok(
                "Department retrieved successfully",
                departmentService.getDepartmentByCode(code)
        );
    }

    @GetMapping(path = "get-all")
    public ResponseEntity<StandardResponse<List<DepartmentDTO>>> getAllDepartment(){
        return ResponseBuilder.ok(
                "Departments retrieved successfully",
                departmentService.getAllDepartment()
        );
    }
}
