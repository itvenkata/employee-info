package com.tvc.employee.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee {
    private Integer empId;
    private String firstName;
    private String lastName;
    private String email;

}
