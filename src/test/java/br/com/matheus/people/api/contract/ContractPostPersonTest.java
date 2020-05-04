package br.com.matheus.people.api.contract;

import br.com.matheus.people.api.clients.PostPersonClient;
import br.com.matheus.people.api.models.Person;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContractPostPersonTest {

    private PostPersonClient postPersonClient = new PostPersonClient();

    @Test
    public void validateContractPostPerson() {
        Person person = Person.builder().name("Matheus").age(26).email("matheus@gmail.com").build();
        postPersonClient.postPerson(person).body(matchesJsonSchema(
                new File("src/test/resources/json_schema/person/person_schema.json")));
    }

    @Test
    public void validateContractPostPersonBadRequest() {
        Person person = Person.builder().build();
        postPersonClient.postPersonBadRequest(person).body(matchesJsonSchema(
                new File("src/test/resources/json_schema/person/bad_request_schema.json")));
    }
}
