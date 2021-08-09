package com.tvc.employee.repository;

import com.tvc.employee.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class EmployeeRepositoryTest {


    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testFindAll() {
        List<Employee> employees = repository.findAll();
        assertEquals(3,employees.size());
    }

    @Test
    public void testFindOne() {
        Employee employee = repository.findById(10001).get();
        assertEquals("chthota@gmail.com",employee.getEmail());
    }
}
