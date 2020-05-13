package br.com.matheus.people.api.functional;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.datadriven.PatchDataProvider;
import br.com.matheus.people.api.models.Person;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.testng.Assert.assertEquals;

public class FunctionalPatchPersonTest extends BaseTest {

    @Test(dataProviderClass = PatchDataProvider.class, dataProvider = "patchNamePerson")
    public void validateFunctionalPatchNamePerson(Person person, String name, String requestBody) {
        Person personUpdated = patchPersonClient.patchPerson(person, requestBody).extract().body().as(Person.class);

        assertEquals(personUpdated.getId(), person.getId());
        assertEquals(personUpdated.getName(), name);
        assertEquals(personUpdated.getAge(), person.getAge());
        assertEquals(personUpdated.getEmail(), person.getEmail());
    }

    @Test(dataProviderClass = PatchDataProvider.class, dataProvider = "patchAgePerson")
    public void validateFunctionalPatchAgePerson(Person person, int age, String requestBody) {
        Person personUpdated = patchPersonClient.patchPerson(person, requestBody).extract().body().as(Person.class);

        assertEquals(personUpdated.getId(), person.getId());
        assertEquals(personUpdated.getName(), person.getName());
        assertEquals(personUpdated.getAge(), age);
        assertEquals(personUpdated.getEmail(), person.getEmail());
    }

    @Test(dataProviderClass = PatchDataProvider.class, dataProvider = "patchEmailPerson")
    public void validateFunctionalPatchEmailPerson(Person person, String email, String requestBody) {
        Person personUpdated = patchPersonClient.patchPerson(person, requestBody).extract().body().as(Person.class);

        assertEquals(personUpdated.getId(), person.getId());
        assertEquals(personUpdated.getName(), person.getName());
        assertEquals(personUpdated.getAge(), person.getAge());
        assertEquals(personUpdated.getEmail(), email);
    }

    @Test(dataProviderClass = PatchDataProvider.class, dataProvider = "patchPersonNotFound")
    public void validateFunctionalPutPersonNotFound(int id, String requestBody) {
        ValidatableResponse response = patchPersonClient.patchPersonNotFound(id, requestBody);

        response.header("content-length", is("0"));
    }
}
