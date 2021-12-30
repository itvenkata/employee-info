package com.tvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

/**
 * This comment looks like javadoc but it at an invalid location. Therefore, the text will not get
 * into TestClass.html and the check will produce a violation.
 */
public class AcceptanceTest implements WireMockService {

  @Autowired private RestTemplate restTemplate;

  @Value("${defaultURL}")
  private String defaultUrl;

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }

  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getDefaultUrl() {
    return defaultUrl;
  }

  public void setDefaultUrl(String defaultUrl) {
    this.defaultUrl = defaultUrl;
  }
}
