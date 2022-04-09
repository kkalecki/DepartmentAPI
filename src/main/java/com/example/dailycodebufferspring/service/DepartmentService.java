package com.example.dailycodebufferspring.service;

import com.example.dailycodebufferspring.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> fetchDepartments();

    Department fetchDepartmentById(Long departmentId);
}
