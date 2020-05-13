package br.com.matheus.people.api.functional;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.datadriven.GetDataProvider;
import br.com.matheus.people.api.models.Person;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class FunctionalGetPersonTest extends BaseTest {

    @Test(dataProviderClass = GetDataProvider.class, dataProvider = "getPeople")
    public void validateFunctionalGetPeople(int index) {
        List<Person> people = getPersonClient.getPeople().extract().jsonPath().getList("", Person.class);
        Person person = getPersonClient.getPerson(people.get(index).getId()).extract().body().as(Person.class);

        assertEquals(people.get(index).getId(), person.getId());
        assertEquals(people.get(index).getName(), person.getName());
        assertEquals(people.get(index).getAge(), person.getAge());
        assertEquals(people.get(index).getEmail(), person.getEmail());
    }

    @Test(dataProviderClass = GetDataProvider.class, dataProvider = "getPerson")
    public void validateFunctionalGetPerson(Person personData) {
        Person person = getPersonClient.getPerson(personData.getId()).extract().body().as(Person.class);

        assertEquals(person.getId(), personData.getId());
        assertEquals(person.getName(), personData.getName());
        assertEquals(person.getAge(), personData.getAge());
        assertEquals(person.getEmail(), personData.getEmail());
    }

    @Test(dataProviderClass = GetDataProvider.class, dataProvider = "getPersonNotFound")
    public void validateFuncionalGetPersonNotFound(int id) {
        ValidatableResponse response = getPersonClient.getPersonNotFound(id);

        response.header("content-length", "0");
    }
}
