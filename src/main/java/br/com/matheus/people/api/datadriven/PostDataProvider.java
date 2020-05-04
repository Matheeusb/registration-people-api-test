package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

public class PostDataProvider {

    private Faker faker = new Faker();

    @DataProvider(name = "postPerson")
    public Object[][] postPerson() {
        return new Object[][] {
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()},
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()},
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()}
        };
    }

    @DataProvider(name = "postPersonNameEmpty")
    public Object[][] postPersonNameEmpty() {
        return new Object[][] {
                {Person.builder().
                        name("").
                        age(faker.number().numberBetween(10, 80)).
                        email(faker.internet().emailAddress()).build()}
        };
    }

    @DataProvider(name = "postPersonEmailEmpty")
    public Object[][] postPersonEmailEmpty() {
        return new Object[][] {
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email("").build()}
        };
    }

    @DataProvider(name = "postPersonEmailMalformed")
    public Object[][] postPersonEmailMalformed() {
        return new Object[][] {
                {Person.builder().
                        name(faker.name().firstName()).
                        age(faker.number().numberBetween(10, 80)).
                        email("testEmail.com").build()}
        };
    }

    @DataProvider(name = "postPersonAgeEqualToZero")
    public Object[][] postPersonAgeEqualToZero() {
        return new Object[][] {
                {Person.builder().
                        name(faker.name().firstName()).
                        age(0).
                        email(faker.internet().emailAddress()).build()}
        };
    }
}
