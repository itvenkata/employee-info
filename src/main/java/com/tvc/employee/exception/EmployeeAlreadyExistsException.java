package com.tvc.employee.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{

    public EmployeeAlreadyExistsException(Integer empId) {
        super("employee already exists for empid: " +empId);
    }
}
