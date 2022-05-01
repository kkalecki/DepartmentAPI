package com.example.dailycodebufferspring.service;

import com.example.dailycodebufferspring.entity.Department;
import com.example.dailycodebufferspring.repository.DepartmentRepository;
import lombok.Builder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {

    private final DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceTest(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("CE")
                .departmentAddress("Liliputki")
                .departmentCode("CE-02")
                .departmentId(1L)
                .build();

        when(departmentRepository.findByDepartmentNameIgnoreCase("CE"))
                .thenReturn(department);
    }

    @Test
    public void whenValidDepartmentName_thenDepartmentShouldFound() {
        String departmentName = "CE";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }
}