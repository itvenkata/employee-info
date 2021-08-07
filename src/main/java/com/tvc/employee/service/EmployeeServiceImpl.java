package com.tvc.employee.service;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.exception.EmployeeNotFoundException;
import com.tvc.employee.mapper.EmployeeMapper;
import com.tvc.employee.vo.EmployeeVO;
import com.tvc.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public EmployeeVO createEmployee(EmployeeVO empVO) {

        return employeeMapper.toEmployeeVO(employeeRepository.save(employeeMapper.toEmployee(empVO)));
    }

    @Override
    public EmployeeVO getEmployee(Integer empId) {
        Optional<Employee> emp =employeeRepository.findById(empId);
        if(emp.isPresent()){
            return employeeMapper.toEmployeeVO(emp.get());
        }else{
            throw new EmployeeNotFoundException(empId);
        }
    }

    @Override
    public void deleteEmployee(Integer empId) {

    }

    @Override
    public EmployeeVO updateEmployee(Integer empId, EmployeeVO updateEmpVO) {
        return null;
    }

    @Override
    public List<EmployeeVO> getAllEmployees() {
        return null;
    }
}
