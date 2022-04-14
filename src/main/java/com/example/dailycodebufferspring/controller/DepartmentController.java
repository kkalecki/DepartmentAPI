package com.example.dailycodebufferspring.controller;

import com.example.dailycodebufferspring.entity.Department;
import com.example.dailycodebufferspring.error.DepartmentNotFoundException;
import com.example.dailycodebufferspring.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;
    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("departments")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        LOGGER.info("Inside saveDepartment of DepartmentController");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("departments")
    public List<Department> fetchDepartments() {
        LOGGER.info("Inside fetchDepartments of DepartmentController");
        return departmentService.fetchDepartments();
    }

    @GetMapping("departments/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentNotFoundException {
        System.out.println("Controller!");
        return departmentService.fetchDepartmentById(departmentId);
    }

    @DeleteMapping("departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
        departmentService.deleteDepartmentById(departmentId);
        return "Department Deleted successfully!!";
    }

    @PutMapping("departments/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department) {
        return departmentService.updateDepartment(departmentId, department);
    }

    @GetMapping("departments/name")
    public Department fetchDepartmentByName(@RequestParam String departmentName) {
        return departmentService.fetchDepartmentByName(departmentName);

    }
}
