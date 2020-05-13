package br.com.matheus.people.api.contract;

import br.com.matheus.people.api.common.BaseTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContractGetPersonTest extends BaseTest {

    @Test
    public void validateContractGetPeople() {
        getPersonClient.getPeople().body(matchesJsonSchema(
                        new File("src/test/resources/json_schema/person/people_schema.json")));
    }

    @Test
    public void validateContractGetPerson() {
        getPersonClient.getPerson(1).body(matchesJsonSchema(
                        new File("src/test/resources/json_schema/person/person_schema.json")));
    }
}
