package com.microservice.department_service.repository;

import com.microservice.department_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    Department getByDepartmentCode(String departmentCode);
}
