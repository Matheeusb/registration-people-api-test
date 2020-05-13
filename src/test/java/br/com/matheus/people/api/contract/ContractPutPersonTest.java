package br.com.matheus.people.api.contract;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.models.Person;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ContractPutPersonTest extends BaseTest {

    @Test
    public void validateContractPutPerson() {
        Person person = Person.builder().id(1).name("Matheus").age(26).email("matheus@gmail.com").build();
        putPersonClient.putPerson(person).body(matchesJsonSchema(
                new File("src/test/resources/json_schema/person/person_schema.json")));
    }

    @Test
    public void validateContractPutPersonBadRequest() {
        Person person = Person.builder().id(1).name(null).age(0).email(null).build();
        putPersonClient.putPersonBadRequest(person).body(matchesJsonSchema(
                new File("src/test/resources/json_schema/person/bad_request_schema.json")));
    }
}
