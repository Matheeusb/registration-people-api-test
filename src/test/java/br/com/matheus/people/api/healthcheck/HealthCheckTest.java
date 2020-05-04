package br.com.matheus.people.api.healthcheck;

import static org.hamcrest.core.Is.is;

import br.com.matheus.people.api.clients.GetActuatorClient;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class HealthCheckTest {

    private GetActuatorClient getActuatorClient = new GetActuatorClient();

    @Test
    public void validateActuator() {
        ValidatableResponse response = getActuatorClient.getActuatorHealth();
        response.body("status", is("UP"));
    }
}
