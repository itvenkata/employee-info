package com.tvc.employee.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(Integer empid) {
        super("employee already exists for empid: '" + empid + "'");
    }
}
