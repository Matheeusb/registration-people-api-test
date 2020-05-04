package br.com.matheus.people.api.functional;

import br.com.matheus.people.api.clients.PostPersonClient;
import br.com.matheus.people.api.datadriven.PostDataProvider;
import br.com.matheus.people.api.models.Person;
import io.restassured.response.ValidatableResponse;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;

public class FunctionalPostPersonTest {

    private PostPersonClient postPersonClient = new PostPersonClient();

    @Test(dataProviderClass = PostDataProvider.class, dataProvider = "postPerson")
    public void validateFunctionalPostPerson(Person personData) {
        Person person = postPersonClient.postPerson(personData).extract().body().as(Person.class);

        assertTrue(person.getId() > 30);
        assertEquals(person.getName(), personData.getName());
        assertEquals(person.getAge(), personData.getAge());
        assertEquals(person.getEmail(), personData.getEmail());
    }

    @Test(dataProviderClass = PostDataProvider.class, dataProvider = "postPersonNameEmpty")
    public void validateFunctionalPostPersonBadRequestNameEmpty(Person person) {
        ValidatableResponse response = postPersonClient.postPersonBadRequest(person);

        response.body("field[0]", is("name"));
        response.body("error[0]", is("must not be empty"));
    }

    @Test(dataProviderClass = PostDataProvider.class, dataProvider = "postPersonEmailEmpty")
    public void validateFunctionalPostPersonBadRequestEmailEmpty(Person person) {
        ValidatableResponse response = postPersonClient.postPersonBadRequest(person);

        response.body("field[0]", is("email"));
        response.body("error[0]", is("must not be empty"));
    }

    @Test(dataProviderClass = PostDataProvider.class, dataProvider = "postPersonEmailMalformed")
    public void validateFunctionalPostPersonBadRequestEmailMalformed(Person person) {
        ValidatableResponse response = postPersonClient.postPersonBadRequest(person);

        response.body("field[0]", is("email"));
        response.body("error[0]", is("must be a well-formed email address"));
    }

    @Test(dataProviderClass = PostDataProvider.class, dataProvider = "postPersonAgeEqualToZero")
    public void validateFunctionalPostPersonBadRequestAgeEqualToZero(Person person) {
        ValidatableResponse response = postPersonClient.postPersonBadRequest(person);

        response.body("field[0]", is("age"));
        response.body("error[0]", is("must be greater than or equal to 1"));
    }
}
