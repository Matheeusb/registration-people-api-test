package br.com.matheus.people.api.functional;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.datadriven.PutDataProvider;
import br.com.matheus.people.api.models.Person;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertEquals;

public class FunctionalPutPersonTest extends BaseTest {

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPerson")
    public void validateFunctionalPutPerson(Person person) {
        Person personUpdated = putPersonClient.putPerson(person).extract().body().as(Person.class);

        assertEquals(personUpdated.getId(), person.getId());
        assertEquals(personUpdated.getName(), person.getName());
        assertEquals(personUpdated.getAge(), person.getAge());
        assertEquals(personUpdated.getEmail(), person.getEmail());
    }

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPersonNameEmpty")
    public void validateFunctionalPutPersonBadRequestNameEmpty(Person person) {
        ValidatableResponse response = putPersonClient.putPersonBadRequest(person);

        response.body("field[0]", is("name"));
        response.body("error[0]", is("must not be empty"));
    }

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPersonEmailEmpty")
    public void validateFunctionalPutPersonBadRequestEmailEmpty(Person person) {
        ValidatableResponse response = putPersonClient.putPersonBadRequest(person);

        response.body("field[0]", is("email"));
        response.body("error[0]", is("must not be empty"));
    }

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPersonEmailMalformed")
    public void validateFunctionalPutPersonBadRequestEmailMalformed(Person person) {
        ValidatableResponse response = putPersonClient.putPersonBadRequest(person);

        response.body("field[0]", is("email"));
        response.body("error[0]", is("must be a well-formed email address"));
    }

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPersonAgeEqualToZero")
    public void validateFunctionalPutPersonBadRequestAgeEqualToZero(Person person) {
        ValidatableResponse response = putPersonClient.putPersonBadRequest(person);

        response.body("field[0]", is("age"));
        response.body("error[0]", is("must be greater than or equal to 1"));
    }

    @Test(dataProviderClass = PutDataProvider.class, dataProvider = "putPersonNotFound")
    public void validateFunctionalPutPersonNotFound(Person person) {
        ValidatableResponse response = putPersonClient.putPersonNotFound(person);

        response.header("content-length", is("0"));
    }
}
