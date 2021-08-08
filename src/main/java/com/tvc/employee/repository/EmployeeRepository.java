package com.tvc.employee.repository;


import com.tvc.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

      public Optional<Employee> findByEmail(String emailId);
}
