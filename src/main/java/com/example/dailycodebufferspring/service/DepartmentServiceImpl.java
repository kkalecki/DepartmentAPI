package com.example.dailycodebufferspring.service;

import com.example.dailycodebufferspring.entity.Department;
import com.example.dailycodebufferspring.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new RuntimeException("Element not Found!"));
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department dbDepartment = departmentRepository.findById(departmentId).get();

        if (department.getDepartmentName() != null) {
            if (!department.getDepartmentName().isBlank()) {
                dbDepartment.setDepartmentName(department.getDepartmentName());
            }
        }
        if (department.getDepartmentAddress() != null) {
            if (!department.getDepartmentAddress().isBlank()) {
                dbDepartment.setDepartmentAddress(department.getDepartmentAddress());
            }
        }
        if (department.getDepartmentCode() != null) {
            if (!department.getDepartmentCode().isBlank()) {
                dbDepartment.setDepartmentCode(department.getDepartmentCode());
            }
        }
        return departmentRepository.save(dbDepartment);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }
}