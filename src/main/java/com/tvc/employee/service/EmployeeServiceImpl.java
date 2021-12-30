package com.tvc.employee.service;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.exception.EmployeeAlreadyExistsException;
import com.tvc.employee.exception.EmployeeNotFoundException;
import com.tvc.employee.mapper.EmployeeMapper;
import com.tvc.employee.repository.EmployeeRepository;
import com.tvc.employee.vo.EmployeeVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired private EmployeeRepository employeeRepository;

  @Autowired private EmployeeMapper employeeMapper;

  @Override
  public EmployeeVo createEmployee(EmployeeVo empVO) {
    Optional<Employee> emp = employeeRepository.findByEmail(empVO.getEmail());
    if (emp.isPresent() && emp.get().getEmail().equals(empVO.getEmail())) {
      throw new EmployeeAlreadyExistsException(empVO.getEmail());
    }
    return employeeMapper.toEmployeeVO(employeeRepository.save(employeeMapper.toEmployee(empVO)));
  }

  @Override
  public EmployeeVo getEmployee(Integer empId) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    if (emp.isPresent()) {
      return employeeMapper.toEmployeeVO(emp.get());
    } else {
      throw new EmployeeNotFoundException(empId);
    }
  }

  @Override
  public void deleteEmployee(Integer empId) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    if (emp.isPresent()) {
      employeeRepository.deleteById(empId);
    } else {
      throw new EmployeeNotFoundException(empId);
    }
  }

  @Override
  public EmployeeVo updateEmployee(Integer empId, EmployeeVo updateEmpVO) {
    Optional<Employee> emp = employeeRepository.findById(empId);
    Employee employee = null;
    if (emp.isPresent()) {
      employee = emp.get();
      employeeRepository.save(employeeMapper.updateEmployee(updateEmpVO, employee));
    } else {
      throw new EmployeeNotFoundException(empId);
    }
    return employeeMapper.toEmployeeVO(employee);
  }

  @Override
  public List<EmployeeVo> getAllEmployees() {
    List<EmployeeVo> voList = new ArrayList<>();
    List<Employee> empList = employeeRepository.findAll();
    if (empList.isEmpty()) {
      return voList;
    } else {
      for (Employee emp : empList) {
        voList.add(employeeMapper.toEmployeeVO(emp));
      }
    }
    return voList;
  }
}
