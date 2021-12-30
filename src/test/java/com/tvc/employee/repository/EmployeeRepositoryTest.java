package com.tvc.employee.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tvc.employee.entity.Employee;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class EmployeeRepositoryTest {

  @Autowired private EmployeeRepository repository;

  @Test
  public void should_create_new_user() {
    Employee emp = new Employee();
    emp.setFirstname("Jacks");
    emp.setLastname("Kulla");
    emp.setEmail("jacks@gmail.com");
    emp.setAddress("5000 W Rand");
    repository.save(emp);

    Optional<Employee> empByEmail = repository.findByEmail("jacks@gmail.com");
    assertThat(empByEmail.get()).isNotNull();
  }

  @Test
  public void testGetEmployee() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    Employee employee2 = repository.findByFirstname("admin");
    assertNotNull(employee);
    assertEquals(employee2.getFirstname(), employee.getFirstname());
    assertEquals(employee2.getLastname(), employee.getLastname());
  }

  @Test
  public void testDeleteEmployee() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    repository.delete(employee);
  }

  @Test
  public void findAllEmployees() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    repository.save(employee);
    assertNotNull(repository.findAll());
  }

  @Test
  public void deletByEmployeeIdTest() {
    Employee employee = new Employee();
    employee.setFirstname("admin");
    employee.setLastname("admin");
    employee.setEmail("admin@gmail.com");
    employee.setAddress("400 W");
    Employee emp = repository.save(employee);
    repository.deleteById(emp.getId());
  }
}
