package br.com.matheus.people.api.healthcheck;

import static org.hamcrest.core.Is.is;

import br.com.matheus.people.api.common.BaseTest;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class HealthCheckTest extends BaseTest {

    @Test
    public void validateActuator() {
        ValidatableResponse response = getActuatorClient.getActuatorHealth();
        response.body("status", is("UP"));
    }
}