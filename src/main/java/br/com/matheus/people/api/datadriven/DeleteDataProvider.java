package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.clients.GetPersonClient;
import br.com.matheus.people.api.configurations.Environment;
import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteDataProvider {

    private Faker faker = new Faker();
    List<Person> list = new ArrayList<>();

    public DeleteDataProvider() {

    }

    @DataProvider(name = "deletePerson")
    public Object[][] deletePerson() {
        list.addAll(new GetPersonClient(Environment.environmentExecution).getPeople().extract().jsonPath().getList("",Person.class));
        Collections.shuffle(list);
        return new Object[][] {
                {list.get(0)}, {list.get(1)}, {list.get(2)}
        };
    }

    @DataProvider(name = "deletePersonNotFound")
    public Object[][] deletePersonNotFound() {
        return new Object[][]{
                {faker.number().numberBetween(100, 110)},
                {faker.number().numberBetween(110, 120)},
                {faker.number().numberBetween(120, 130)}
        };
    }
}
