package com.tvc.employee.service;

import com.tvc.employee.model.Employee;
import com.tvc.employee.util.EmployeeUtil;

import java.util.List;

public class EmployeeService {
    //CRUD


    public String addEmployee(Employee emp) {
        String msg=null;
        if(emp.getEmpId() !=null) {
            EmployeeUtil.empMap.put(emp.getEmpId(), emp);
            msg="Employee Added Succesfully";
        }else{
            msg="Employedd Added Failed";
        }
        return msg;
    }

    public Employee getEmployee(Integer empId){

        return null;
    }

    public Employee updateEmployee(Integer empId,Employee emp){

        return null;
    }

    public String deleteEmployee(Integer empId){

        return null;
    }

  public List<Employee> getAllEmployees(){

        return null;
    }



}
