package com.tvc.employee.controller;

import com.tvc.employee.service.EmployeeService;
import com.tvc.employee.vo.EmployeeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class EmployeeController {

   @Autowired
   EmployeeService  employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeVO emp){

        employeeService.addEmployee(emp);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
