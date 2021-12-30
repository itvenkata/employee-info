Feature: Test CRUD methods in Sample Employee REST API testing

  Scenario: Add Employee record
    Given I Set POST employee service api endpoint
    When Send a POST HTTP request
    Then I receive valid Response

  Scenario: Get Employee record
    Given User enters the Employee ID "222222222"
    When The user makes a call to get the employee details
    Then The API should return the Employee Details

  Scenario: Update Employee record
    Given User enters the Employee ID "222222222" for update
    When I Set Update request Body
    Then receive updated http status code 200

  Scenario: Delete Employee record
    Given I Set DELETE employee service api endpoint
    When Send a DELETE HTTP request
    Then receive deleted http status code 200

