package com.microservice.employee_service.service;

import com.microservice.employee_service.dto.DepartmentDTO;
import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8082", name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/api/department/get-by-code")
    ResponseEntity<StandardResponse<DepartmentDTO>> getDepartmentByCode(@RequestParam("code") String code);
}
