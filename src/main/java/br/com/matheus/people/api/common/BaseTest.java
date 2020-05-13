package br.com.matheus.people.api.common;

import br.com.matheus.people.api.clients.*;
import br.com.matheus.people.api.configurations.Environment;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class BaseTest {

    protected GetActuatorClient getActuatorClient;
    protected GetPersonClient getPersonClient;
    protected PostPersonClient postPersonClient;
    protected PutPersonClient putPersonClient;
    protected PatchPersonClient patchPersonClient;
    protected DeletePersonClient deletePersonClient;

    @BeforeClass
    @Parameters("environment")
    public void setUp(@Optional("Homolog") String environment) {
        getActuatorClient = new GetActuatorClient(environment);
        getPersonClient = new GetPersonClient(environment);
        postPersonClient = new PostPersonClient(environment);
        putPersonClient = new PutPersonClient(environment);
        patchPersonClient = new PatchPersonClient(environment);
        deletePersonClient = new DeletePersonClient(environment);

        Environment.environmentExecution = environment;
    }
}
