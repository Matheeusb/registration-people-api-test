package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.clients.GetPersonClient;
import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetDataProvider {

    private Faker faker = new Faker();
    private List<Person> list = new ArrayList<>();

    @DataProvider(name = "getPeople")
    public Object[][] getPeople() {
        return new Object[][]{
                {faker.number().numberBetween(1, 10)},
                {faker.number().numberBetween(11, 20)},
                {faker.number().numberBetween(21, 30)}
        };
    }

    @DataProvider(name = "getPerson")
    public Object[][] getPerson() {
        list.addAll(new GetPersonClient().getPeople().extract().jsonPath().getList("",Person.class));
        Collections.shuffle(list);
        return new Object[][]{
                {list.get(0)}, {list.get(1)}, {list.get(2)}
        };
    }

    @DataProvider(name = "getPersonNotFound")
    public Object[][] getPersonNotFound() {
        return new Object[][]{
                {faker.number().numberBetween(100, 110)},
                {faker.number().numberBetween(111, 120)},
                {faker.number().numberBetween(121, 130)}
        };
    }
}
