package com.tvc;

import com.tvc.employee.EmployeeInfoApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberContextConfiguration
@SpringBootTest(
    classes = EmployeeInfoApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("acceptance-test")
@CucumberOptions(
    features = "acceptance-test/features/resources",
    plugin = {"pretty", "html:build/reports/cucumber/cucumber-report.html"})
public class EmployeeTest {

}
