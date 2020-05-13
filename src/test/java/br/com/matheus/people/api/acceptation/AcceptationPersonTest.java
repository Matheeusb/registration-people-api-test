package br.com.matheus.people.api.acceptation;

import br.com.matheus.people.api.common.BaseTest;
import br.com.matheus.people.api.datadriven.AcceptationDataProvider;
import br.com.matheus.people.api.models.Person;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AcceptationPersonTest extends BaseTest {

    @Test(dataProviderClass = AcceptationDataProvider.class, dataProvider = "acceptationPerson")
    public void validateAcceptationPerson(Person person, Person newPerson) {
        Person personCreated = postPersonClient.postPerson(person).extract().body().as(Person.class);

        Person verifiedCreatedPerson = getPersonClient.getPerson(personCreated.getId()).extract().body().as(Person.class);
        assertEquals(verifiedCreatedPerson.getName(), person.getName());
        assertEquals(verifiedCreatedPerson.getAge(), person.getAge());
        assertEquals(verifiedCreatedPerson.getEmail(), person.getEmail());

        newPerson.setId(personCreated.getId());
        Person personUpdated = putPersonClient.putPerson(newPerson).extract().body().as(Person.class);

        Person verifiedUpdatedPerson = getPersonClient.getPerson(newPerson.getId()).extract().body().as(Person.class);
        assertEquals(verifiedUpdatedPerson.getId(), newPerson.getId());
        assertEquals(verifiedUpdatedPerson.getName(), newPerson.getName());
        assertEquals(verifiedUpdatedPerson.getAge(), newPerson.getAge());
        assertEquals(verifiedUpdatedPerson.getEmail(), newPerson.getEmail());

        deletePersonClient.deletePerson(personUpdated.getId());
        getPersonClient.getPersonNotFound(personUpdated.getId());
    }
}
