package com.tvc.employee.main;

import com.tvc.employee.model.Employee;
import com.tvc.employee.service.EmployeeService;
import com.tvc.employee.util.EmployeeUtil;
import lombok.extern.log4j.Log4j;

import java.util.List;

@Log4j
public class Main {

    public static void main(String[] args) {
        try {
            EmployeeService empService = new EmployeeService();
            empService.addEmployee(createEmployee(234, "Venkata", "Thota", "chthota@gmail.com"));
            empService.addEmployee(createEmployee(254, "Jasmi", "Thota", "jasi@gmail.com"));
            empService.addEmployee(createEmployee(254, "Jasmi", "Thota", "jasi@gmail.com"));
            empService.addEmployee(createEmployee(258, "Jasmi", "Thota", "jasi@gmail.com"));
            empService.addEmployee(createEmployee(258, "Jasmi", "Thota", "jasi@gmail.com"));
           log.info("Employee Map Size::::" + EmployeeUtil.empMap.size());
            try {
                Employee emp1 = empService.getEmployee(258);
                log.info(emp1);
                Employee emp2=empService.updateEmployee(258,createEmployee(777,"James","Kulla","kulla@gmail.com"));
               List<Employee> empList=empService.getAllEmployees();
               for(Employee emp6:empList){
                   log.info("Employee from GetAll"+emp6);
               }
                log.info(emp2);
               String status= empService.deleteEmployee(99999);

                log.info(status);



            } catch (Exception e) {
                log.error(e.getMessage());
            }


            //  System.out.println(empService.getEmployee(254).toString());
        } catch (Exception e) {
           log.error(e.getMessage());
        }
    }

    static Employee createEmployee(Integer empId, String firstName, String lastName, String email) {
        Employee emp = new Employee();
        emp.setEmpId(empId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setEmail(email);

        return emp;

    }
}
