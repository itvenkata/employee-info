package com.tvc.employee.repository;

import com.tvc.employee.entity.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  public Optional<Employee> findByEmail(String emailId);

  Employee findByFirstname(String username);
}
