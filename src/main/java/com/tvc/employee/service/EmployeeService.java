package com.tvc.employee.service;

import com.tvc.employee.exception.EmployeeAlreadyExistsException;
import com.tvc.employee.exception.EmployeeNotFoundException;
import com.tvc.employee.model.Employee;
import com.tvc.employee.util.EmployeeUtil;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j
public class EmployeeService {   //CRUD



    public String addEmployee(Employee emp) {
        log.info("Start Method addEmployee ");
        String msg = null;
        try {
            if (EmployeeUtil.empMap.containsKey(emp.getEmpId())) {
                throw new EmployeeAlreadyExistsException(emp.getEmpId());
            } else {
                EmployeeUtil.empMap.put(emp.getEmpId(), emp);
                msg = "Employee Added Succesfully";
            }
        } catch (EmployeeAlreadyExistsException e) {
            log.error(e.getMessage());
        }
        log.info("End Method addEmployee ");
        return msg;
    }

    public Employee getEmployee(Integer empId) {
        log.info("Start Method getEmployee ");
        Employee emp = EmployeeUtil.empMap.get(empId);
        if (emp != null) {
            return emp;
        } else {
            throw new EmployeeNotFoundException(empId);
        }

    }

    public Employee updateEmployee(Integer empId, Employee emp) {
        log.info("Start Method updateEmployee ");
        Employee currentEmp = null;
        try {
            currentEmp = getEmployee(empId);
            currentEmp.setLastName(emp.getLastName());
            currentEmp.setFirstName(emp.getFirstName());
            currentEmp.setEmail(emp.getEmail());
            EmployeeUtil.empMap.put(empId,currentEmp);

        }catch(Exception e){
            log.error(e.getMessage());
        }

        log.info("End Method updateEmployee ");
        return currentEmp;
    }

    public String deleteEmployee(Integer empId) {
        log.info("Start Method deleteEmployee ");
        Employee emp = getEmployee(empId);
        EmployeeUtil.empMap.remove(empId);
        log.info("End Method deleteEmployee ");
        return "Employee deteleted successfully ::"+empId;
    }

    public List<Employee> getAllEmployees() {
        log.info("Start Method getAllEmployees ");
        List<Employee> empList = new ArrayList<>();
        for (Map.Entry<Integer,Employee> me : EmployeeUtil.empMap.entrySet()) {
            //System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
            empList.add(me.getValue());

        }
        log.info("End Method getAllEmployees ");

        return empList;
    }


}
