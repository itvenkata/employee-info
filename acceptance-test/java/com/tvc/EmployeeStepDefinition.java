package com.tvc;

import static org.junit.Assert.assertEquals;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.web.client.RestTemplate;

/**
 * This comment looks like javadoc but it at an invalid location. Therefore, the text will not get
 * into TestClass.html and the check will produce a violation.
 */
public class EmployeeStepDefinition extends AcceptanceTest {

  private static final String APPLICATION_JSON = "application/json";

  String empId;

  String url;

  HttpHeaders httpHeaders;

  ResponseEntity<String> response;

  ResponseEntity<?> deleteResponse;

  RestTemplate restTemplate;

  /** before test case. */
  @Before
  public void setup() {
    startService();
    configureFor("localhost", 8005);
    httpHeaders = new HttpHeaders();
    httpHeaders.add("Accept", "application/json");
    httpHeaders.add("Content-Type", "application/json");
    restTemplate = getRestTemplate();
  }

  @Given("^I Set POST employee service api endpoint$")
  public void setCreateEmployeeEndpoint() throws Exception {
    url = getDefaultUrl() + "api/employee/v1/create";
  }

  /** employee post request. */
  @When("^Send a POST HTTP request$")
  public void sendEmployeePostRequest() throws Exception {
    stubFor(
        post(urlEqualTo("/api/employee/v1/create"))
            .withHeader("accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(
                aResponse()
                    .withBodyFile("employee_response.json")
                    .withStatus(HttpStatus.CREATED.value())));
    String jsonBody =
        JsonUtil.readFileAsString("acceptance-test/resources/stubs/employee_response.json");
    HttpEntity<String> entity = new HttpEntity<String>(jsonBody, httpHeaders);
    response = restTemplate.postForEntity(url, entity, String.class);
  }

  /** return employee. */
  @Then("^I receive valid Response$")
  public void shouldReturnEmployeeDetails() throws Exception {
    assertEquals(201, response.getStatusCodeValue());
    Assert.hasText(
        response.getBody(),
        JsonUtil.readFileAsString("acceptance-test/resources/stubs/employee_response.json"));
  }

  @Given("^User enters the Employee ID \"([^\"]*)\"$")
  public void requesting_details_for(String empId) throws Throwable {
    this.empId = empId;
    url = getDefaultUrl() + "api/employee/v1/" + empId;
  }

  /** api call. */
  @When("^The user makes a call to get the employee details$")
  public void an_api_call_is_being_made() throws Exception {
    stubFor(
        get(urlEqualTo("/api/employee/v1/" + empId))
            .withHeader("accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(aResponse().withBodyFile("employee_response.json")));
    HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
    response =
        restTemplate.exchange(
            "http://localhost:" + 8005 + "/api/employee/v1/" + empId,
            HttpMethod.GET,
            requestEntity,
            String.class);
  }

  /** api call. */
  @Then("^The API should return the Employee Details$")
  public void should_return_the_Companies_House_Details() throws Throwable {
    assertEquals(200, response.getStatusCodeValue());
    Assert.hasText(
        response.getBody(),
        JsonUtil.readFileAsString("acceptance-test/resources/stubs/employee_response.json"));
  }

  @Given("^User enters the Employee ID \"([^\"]*)\" for update$")
  public void putEmployeeServiceApiEndpoint(String empId) throws Throwable {
    this.empId = empId;
    url = getDefaultUrl() + "api/employee/v1/" + empId;
  }

  /** request body. */
  @When("I Set Update request Body")
  public void set_update_request_body() throws Exception {
    stubFor(
        put(urlEqualTo("/api/employee/v1/" + empId))
            .withHeader("accept", equalTo(MediaType.APPLICATION_JSON_VALUE))
            .willReturn(
                aResponse()
                    .withBodyFile("update_employee.json")
                    .withStatus(HttpStatus.OK.value())));

    String jsonBody =
        JsonUtil.readFileAsString("acceptance-test/resources/stubs/update_employee.json");

    HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, httpHeaders);

    deleteResponse = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
  }

  @Then("receive updated http status code {int}")
  public void receive_valid_http_response_code(long int1) throws Exception {
    assertEquals(int1, deleteResponse.getStatusCodeValue());
  }

  @Given("I Set DELETE employee service api endpoint")
  public void set_delete_employee_service_api_endpoint() {
    url = getDefaultUrl() + "api/employee/v1/2222";
  }

  /** delete http request. */
  @When("Send a DELETE HTTP request")
  public void send_a_delete_http_request() {
    stubFor(
        delete(urlEqualTo("/api/employee/v1/2222"))
            .withHeader("content-type", equalTo(APPLICATION_JSON))
            .willReturn(aResponse().withStatus(200)));
    HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

    deleteResponse = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
  }

  @Then("receive deleted http status code {int}")
  public void receive_deleted_http_status_code(long int1) {
    assertEquals(int1, deleteResponse.getStatusCodeValue());
  }

  @After
  public final void tearDown() {
    //  stopService();
  }
}
