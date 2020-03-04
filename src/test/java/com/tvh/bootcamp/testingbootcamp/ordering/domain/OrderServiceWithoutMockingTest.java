package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import org.junit.jupiter.api.Test;

public class OrderServiceWithoutMockingTest {

    @Test
    void create_Order() {
        //Arrange - Set up an orderService which used an InMemoryOrderRepository and
        //        - Create a new Order

        //Act - Execute orderService#createOrUpdate with the previously set up order. Capture the result.

        //Assert - Assert that the captured resulting order is the same as the saved order from the arrange section
    }

    @Test
    void find_Order() {
        //Arrange - Set up an orderService which used an InMemoryOrderRepository and
        //        - Create a new Order and
        //        - Save the order

        //Act - Execute orderService#find with the orderId of the order we previously saved

        //Assert - verify that we find an Order AND that it is the same order we saved in the Arrange section
    }

    @Test
    void cannot_find_an_Order_with_unknown_id() {
        //Arrange - Set up an orderService which used an InMemoryOrderRepository and
        //        - Create a random UUID

        //Act + Assert - AssertThat orderService#find with the random UUID results in an OrderNotFoundException being thrown
    }
}
