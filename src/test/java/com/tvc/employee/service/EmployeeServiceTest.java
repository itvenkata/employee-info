package com.tvc.employee.service;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.exception.EmployeeAlreadyExistsException;
import com.tvc.employee.mapper.EmployeeMapper;
import com.tvc.employee.repository.EmployeeRepository;
import com.tvc.employee.vo.EmployeeVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
// @SpringBootTest
class EmployeeServiceTest {

  @InjectMocks private EmployeeServiceImpl employeeServiceImpl;

  @Mock private EmployeeRepository employeeRepository;

  @InjectMocks private EmployeeMapper mapper;

  @Mock private EmployeeMapper employeeMapper;

  @Test
  void createEmployeeSuccess() {

    EmployeeVO employeeVO = new EmployeeVO();
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

    EmployeeVO employeeVO = new EmployeeVO();
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
  void getEmployee() {}

  @Test
  void deleteEmployee() {}

  @Test
  void updateEmployee() {}

  @Test
  void getAllEmployees() {}
}
