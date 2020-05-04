package br.com.matheus.people.api.datadriven;

import br.com.matheus.people.api.clients.GetPersonClient;
import br.com.matheus.people.api.models.Person;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PatchDataProvider {

    private Faker faker = new Faker();
    private List<Person> list = new ArrayList<>();
    private String requestBodyString = "{\"%s\": \"%s\"}";
    private String requestBodyInt = "{\"%s\": %d}";

    @DataProvider(name = "patchNamePerson")
    public Object[][] patchNamePerson() {
        list.addAll(new GetPersonClient().getPeople().extract().jsonPath().getList("", Person.class));
        Collections.shuffle(list);
        String attribute = "name";

        Person person1 = list.get(0);
        String nameFaker1 = faker.name().fullName();
        String nameBody1 = String.format(requestBodyString, attribute, nameFaker1);

        Person person2 = list.get(1);
        String nameFaker2 = faker.name().fullName();
        String nameBody2 = String.format(requestBodyString, attribute, nameFaker2);

        Person person3 = list.get(2);
        String nameFaker3 = faker.name().fullName();
        String nameBody3 = String.format(requestBodyString, attribute, nameFaker3);

        return new Object[][]{
                {person1, nameFaker1, nameBody1},
                {person2, nameFaker2, nameBody2},
                {person3, nameFaker3, nameBody3}
        };
    }

    @DataProvider(name = "patchAgePerson")
    public Object[][] patchAgePerson() {
        list.addAll(new GetPersonClient().getPeople().extract().jsonPath().getList("", Person.class));
        Collections.shuffle(list);
        String attribute = "age";

        Person person1 = list.get(0);
        int ageFaker1 = faker.number().numberBetween(10, 80);
        String ageBody1 = String.format(requestBodyInt, attribute, ageFaker1);

        Person person2 = list.get(1);
        int ageFaker2 = faker.number().numberBetween(10, 80);
        String ageBody2 = String.format(requestBodyInt, attribute, ageFaker2);

        Person person3 = list.get(2);
        int ageFaker3 = faker.number().numberBetween(10, 80);
        String ageBody3 = String.format(requestBodyInt, attribute, ageFaker3);

        return new Object[][]{
                {person1, ageFaker1, ageBody1},
                {person2, ageFaker2, ageBody2},
                {person3, ageFaker3, ageBody3}
        };
    }

    @DataProvider(name = "patchEmailPerson")
    public Object[][] patchEmailPerson() {
        list.addAll(new GetPersonClient().getPeople().extract().jsonPath().getList("", Person.class));
        Collections.shuffle(list);
        String attribute = "email";

        Person person1 = list.get(0);
        String emailFaker1 = faker.internet().emailAddress();
        String emailBody1 = String.format(requestBodyString, attribute, emailFaker1);

        Person person2 = list.get(1);
        String emailFaker2 = faker.internet().emailAddress();
        String emailBody2 = String.format(requestBodyString, attribute, emailFaker2);

        Person person3 = list.get(2);
        String emailFaker3 = faker.internet().emailAddress();
        String emailBody3 = String.format(requestBodyString, attribute, emailFaker3);

        return new Object[][]{
                {person1, emailFaker1, emailBody1},
                {person2, emailFaker2, emailBody2},
                {person3, emailFaker3, emailBody3}
        };
    }

    @DataProvider(name = "patchPersonNotFound")
    public Object[][] patchPersonNotFound() {
        String attribute = "name";

        int id = faker.number().numberBetween(100, 120);
        String nameFaker = faker.name().fullName();
        String nameBody = String.format(requestBodyString, attribute, nameFaker);

        return new Object[][]{
                {id, nameBody}
        };
    }
}
