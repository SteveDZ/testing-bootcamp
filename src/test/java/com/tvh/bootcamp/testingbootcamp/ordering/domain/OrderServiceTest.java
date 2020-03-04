package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import org.junit.jupiter.api.Test;

//Extend this class with MockitoExtensions In order for the Mockito annotations to be processed.
class OrderServiceTest {

    //Create OrderRepository mock

    //Create Mockito fixture. Make sure the Mocks are being injected in the fixture

    @Test
    void create_Order() {
        //Arrange - Create an order and mock the orderRepository#save call

        //Act - Execute orderService#createOrUpdate with the order we previously set up.

        //Assert - assert that the saved order is the same as the order we've set up in the Arrange section and which is being returned by our orderRepository mock.
        //Assert - verify that the orderRepository#save call was executed
    }

    @Test
    void find_Order() {
        //Arrange - Create an order and mock the orderRepository#findById call

        //Act - Execute the orderService#find call with the order we previously set up

        //Assert - assert that the order we found is the same as the order being returned from our orderRepository mock
    }

    @Test
    void cannot_find_an_Order_with_unknown_id() {
        //Arrange - Create an unknown UUID

        //Act - assert that an exception is being thrown when orderService#find is being called with an unknown order UUID

        //Assert - verify that the orderRepository#findById method was called with our unknown orderId
    }
}
