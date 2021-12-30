package com.tvc.employee.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.exception.EmployeeAlreadyExistsException;
import com.tvc.employee.exception.EmployeeNotFoundException;
import com.tvc.employee.mapper.EmployeeMapper;
import com.tvc.employee.repository.EmployeeRepository;
import com.tvc.employee.vo.EmployeeVo;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
// @SpringBootTest
class EmployeeServiceTest {

  @InjectMocks private EmployeeServiceImpl employeeServiceImpl;

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeMapper mapper;

  @Mock private EmployeeMapper employeeMapper;

  @Test
  void createEmployeeSuccess() {

    EmployeeVo employeeVO = new EmployeeVo();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    String emil = "test@gmail.com";

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findByEmail(emil)).thenReturn(emp);
    when(employeeMapper.toEmployeeVO(
            employeeRepository.save(employeeMapper.toEmployee(employeeVO))))
        .thenReturn(employeeVO);
    employeeServiceImpl.createEmployee(employeeVO);
  }

  @Test
  void createEmployeeFailure() {

    EmployeeVo employeeVO = new EmployeeVo();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findByEmail(employeeVO.getEmail())).thenReturn(emp);
    when(employeeMapper.toEmployeeVO(
            employeeRepository.save(employeeMapper.toEmployee(employeeVO))))
        .thenReturn(employeeVO);

    assertThrows(
        EmployeeAlreadyExistsException.class,
        () -> {
          employeeServiceImpl.createEmployee(employeeVO);
        });

    ;
  }

  @Test
  void getEmployeeFound() {

    EmployeeVo employeeVO = new EmployeeVo();
    employeeVO.setId(101);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");

    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findById(12345)).thenReturn(emp);
    employeeServiceImpl.getEmployee(12345);
    assertNotNull(employeeVO);
    assertEquals("James", employeeVO.getFirstname());
  }

  @Test
  void getEmployeeNotFound() {

    Optional<Employee> emp = Optional.empty();
    when(employeeRepository.findById(12345)).thenReturn(emp);
    assertThrows(
        EmployeeNotFoundException.class,
        () -> {
          employeeServiceImpl.getEmployee(12345);
        });
  }

  @Test
  void deleteEmployee() {}

  @Test
  void updateEmployeeSuccess() {

    EmployeeVo employeeVO = new EmployeeVo();
    employeeVO.setId(1234);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");
    Optional<Employee> emp = Optional.of(mapper.toEmployee(employeeVO));
    when(employeeRepository.findById(1234)).thenReturn(emp);
    employeeServiceImpl.updateEmployee(1234, employeeVO);
  }

  @Test
  void updateEmployeeFailed() {

    EmployeeVo employeeVO = new EmployeeVo();
    employeeVO.setId(1234);
    employeeVO.setFirstname("James");
    employeeVO.setLastname("Kulla");
    employeeVO.setEmail("james@gmail.com");
    employeeVO.setAddress("400 W Rand");
    Optional<Employee> emp = Optional.empty();
    assertThrows(
        EmployeeNotFoundException.class,
        () -> {
          employeeServiceImpl.updateEmployee(1234, employeeVO);
        });
  }

  @Test
  void getAllEmployees() {}
}
