package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class AcceptationDataProvider {

    private Faker faker = new Faker();

    @DataProvider(name = "acceptationPerson")
    public Object[][] accepatationPerson() {
        Person person = Person.builder().
                name(faker.name().firstName()).
                age(faker.number().numberBetween(10, 80)).
                email(faker.internet().emailAddress()).build();

        Person newPerson = Person.builder().
                name(faker.name().firstName()).
                age(faker.number().numberBetween(10, 80)).
                email(faker.internet().emailAddress()).build();

        return new Object[][]{
                {person, newPerson}
        };
    }
}
