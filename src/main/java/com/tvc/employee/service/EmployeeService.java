package com.tvc.employee.service;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.vo.EmployeeVO;
import com.tvc.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;



    public void addEmployee(EmployeeVO employee){
        employeeRepository.save(mapVOToEntity(employee));
    }

    public EmployeeVO getEmployee(Integer empId){

        return null;

    }

    public String deleteEmployee(Integer empId){

        return null;
    }

    public EmployeeVO updateEmployee(Integer empId, EmployeeVO updatedEmp){

        return null;
    }

    public List<EmployeeVO> getAllEmployees(){

        return null;
    }

    private Employee mapVOToEntity(EmployeeVO vo){

        Employee emp = new Employee();
        emp.setFirstname(vo.getFirstname());
        emp.setLastname(vo.getLastname());
        emp.setEmail(vo.getEmail());
        emp.setAddress(vo.getAddress());

        return emp;

    }

}
