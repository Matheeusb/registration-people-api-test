package br.com.matheus.people.api.functional;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.datadriven.DeleteDataProvider;
import br.com.matheus.people.api.models.Person;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

public class FunctionalDeletePersonTest extends BaseTest {

    @Test(dataProviderClass = DeleteDataProvider.class, dataProvider = "deletePerson")
    public void validateFunctionalDeletePerson(Person person) {
        ValidatableResponse response = deletePersonClient.deletePerson(person.getId());

        response.header("content-length", "0");
    }

    @Test(dataProviderClass = DeleteDataProvider.class, dataProvider = "deletePersonNotFound")
    public void validateFunctionalDeletePersonNotFound(int id) {
        ValidatableResponse response = deletePersonClient.deletePersonNotFound(id);

        response.header("content-length", "0");
    }
}
