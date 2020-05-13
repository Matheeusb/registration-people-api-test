# Registration of People - Test

The purpose of this project is to perform automated tests on the API Registration of People.

## Language and frameworks

- Java 13
- Maven
- TestNG
- Json Schema Validator
- Jackson Databind
- Java Faker

## Pre requisites

- Clone the Registration of People - Client project
- Run the ```mvn clean install``` command

## Validation strategy

- Health Check - Validate that the API is in UP status

- Contract Test - Validate the contract of attributes established for the endpoint

- Functional Test - Validate that the endpoint returns the expected data

- Acceptation Test - Validate that the set of endpoints work together

## Test suites

The test suites can be executed via the command line, being necessary to inform the execution environment:

```shell script
mvn clean test -Dsuite=Contract -Denvironment=Homolog
```

```shell script
mvn clean test -Dsuite=Functional -Denvironment=Dev
```

## Links

- [Registration of People - API](https://github.com/Matheeusb/registration-people-api)
- [Registration of People - Client](https://github.com/Matheeusb/registration-people-api-client)