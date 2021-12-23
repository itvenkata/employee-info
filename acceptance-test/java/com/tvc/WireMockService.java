package com.tvc;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
/*import com.uk.companieshouse.model.CompaniesHouseGovUKResponse;
import org.junit.Rule;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.uk.companieshouse.utils.TestHelper.TESTCRN;
import static org.junit.jupiter.api.Assertions.assertEquals;*/

public interface WireMockService {

  @Rule
  WireMockRule WIRE_MOCK_RULE = new WireMockRule(WireMockConfiguration.wireMockConfig().port(8005));

  default void startService() {
    WIRE_MOCK_RULE.start();
  }

  default void stopService() {
    WIRE_MOCK_RULE.stop();
  }
}
