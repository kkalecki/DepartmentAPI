package com.example.dailycodebufferspring.service;

import com.example.dailycodebufferspring.entity.Department;
import com.example.dailycodebufferspring.error.DepartmentNotFoundException;
import com.example.dailycodebufferspring.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(department.isEmpty()) {
            throw new DepartmentNotFoundException("Department not found!");
        }
        return department.get();
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