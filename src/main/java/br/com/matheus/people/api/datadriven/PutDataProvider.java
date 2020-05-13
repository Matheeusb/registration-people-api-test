package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.clients.GetPersonClient;
import br.com.matheus.people.api.configurations.Environment;
import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PutDataProvider  {

    private Faker faker = new Faker();
    private List<Person> list = new ArrayList<>();

    @DataProvider(name = "putPerson")
    public Object[][] putPerson() {
        list.addAll(new GetPersonClient(Environment.environmentExecution).getPeople().extract().jsonPath().getList("",Person.class));
        Collections.shuffle(list);
        Person person1 = list.get(0);
        person1.setName(faker.name().firstName());
        person1.setAge(faker.number().numberBetween(10, 80));
        person1.setEmail(faker.internet().emailAddress());

        Person person2 = list.get(1);
        person2.setName(faker.name().firstName());
        person2.setAge(faker.number().numberBetween(10, 80));
        person2.setEmail(faker.internet().emailAddress());

        Person person3 = list.get(2);
        person3.setName(faker.name().firstName());
        person3.setAge(faker.number().numberBetween(10, 80));
        person3.setEmail(faker.internet().emailAddress());
        return new Object[][]{
                {person1}, {person2}, {person3}
        };
    }

    @DataProvider(name = "putPersonNameEmpty")
    public Object[][] putPersonNameEmpty() {
        return new Object[][]{
                {Person.builder().
                        name("").
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()}
        };
    }

    @DataProvider(name = "putPersonEmailEmpty")
    public Object[][] putPersonEmailEmpty() {
        return new Object[][]{
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email("").build()}
        };
    }

    @DataProvider(name = "putPersonEmailMalformed")
    public Object[][] putPersonEmailMalformed() {
        return new Object[][]{
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email("test.com").build()}
        };
    }

    @DataProvider(name = "putPersonAgeEqualToZero")
    public Object[][] putPersonAgeEqualToZero() {
        return new Object[][]{
                {Person.builder().
                        name(faker.name().firstName()).
                        age(0).
                        email(faker.internet().emailAddress()).build()}
        };
    }

    @DataProvider(name = "putPersonNotFound")
    public Object[][] putPersonNotFound() {
        return new Object[][]{
                {Person.builder().
                        id(faker.number().numberBetween(100, 120)).
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()}
        };
    }
}
