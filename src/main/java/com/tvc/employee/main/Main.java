package com.tvc.employee.main;

import com.tvc.employee.model.Employee;
import com.tvc.employee.service.EmployeeService;
import com.tvc.employee.util.EmployeeUtil;

public class Main {

    public static void main(String args[]){

        EmployeeService empService = new EmployeeService();
        empService.addEmployee(createEmployee(234,"Venkata","Thota","chthota@gmail.com"));
        empService.addEmployee(createEmployee(254,"Jasmi","Thota","jasi@gmail.com"));
        empService.addEmployee(createEmployee(254,"Jasmi","Thota","jasi@gmail.com"));
        System.out.println("Employee Map Size::::"+ EmployeeUtil.empMap.size());
    }

    static Employee createEmployee(Integer empId, String firstName, String lastName, String email){
        Employee emp= new Employee();
        emp.setEmpId(empId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setEmail(email);

        return emp;

    }
}
