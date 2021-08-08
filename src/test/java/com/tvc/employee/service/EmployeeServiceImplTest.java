package com.tvc.employee.service;

import com.tvc.employee.exception.EmployeeAlreadyExistsException;
import com.tvc.employee.mapper.EmployeeMapper;
import com.tvc.employee.vo.EmployeeVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService empService;

    private EmployeeVO empVO;

    @BeforeEach
    void setUp() {
        empVO = new EmployeeVO();
        empVO.setFirstname("Venkata");
        empVO.setLastname("Thota");
        empVO.setEmail("chthota@gmail.com");
        empVO.setAddress("400 W Rand Road");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Sould Create New Employee Successfully")
    void createEmployeeSuccess() {
        EmployeeVO employeeVO =empService.createEmployee(empVO);
        assertEquals(1,employeeVO.getId());
        assertNotNull(employeeVO);
    }

    @Test
    @DisplayName("should throws EmployeeAlreadyExistsExceptionwhen creating employee")
    void createEmployeeThrowsEmployeeAlreadyExistsException(){
        assertThrows(EmployeeAlreadyExistsException.class,()->{
            empService.createEmployee(empVO);
        });

    }



    @Test
    void getEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void getAllEmployees() {
    }
}