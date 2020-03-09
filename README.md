# TVH Java Testing bootcamp

## Exercise 1

Checkout branch exercise_1/unit_tests

Implement the tests in the following 3 Test classes

- OrderServiceWithoutMockingTest
- OrderTest
- PriceInEuroTest 


## Exercise 1 - Continued

Still checkout branch exercise_1/unit_tests

Implement the following test

- OrderServiceTest

## Exercise 2 - Fixtures, @Before

Checkout branch exercise_2/fixture_at_before

Implement the changes in following test

- OrderTest
- OrderServiceWithoutMockingTest

## Exercise 3 - Fixtures, with ObjectMothers

Checkout branch exercise_3/fixture_with_object_mothers

Implement

- OrderMother
- OrderTest

## Exercise 4 - Fixtures, Test Data Builders

Checkout branch exercise_4/test_data_builders

Implement OrderTest with OrderBuilder

## Running the docker postgres database

```$ docker run --name bootcamp-postgres -p 5432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testing_bootcamp -d postgres:9.6 -rm```
