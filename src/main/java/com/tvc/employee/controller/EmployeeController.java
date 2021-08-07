package com.tvc.employee.controller;

import com.tvc.employee.service.EmployeeServiceImpl;
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
   EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<EmployeeVO> createEmployee(@RequestBody EmployeeVO emp){

            return new ResponseEntity(employeeServiceImpl.createEmployee(emp), HttpStatus.CREATED);

    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeVO> getEmployee(@PathVariable Integer empId){
            return new ResponseEntity(employeeServiceImpl.getEmployee(empId), HttpStatus.OK);
    }

}
