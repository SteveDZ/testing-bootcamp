package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void is_not_Picked() {
        //Arrange - Create a random orderId UUID
        //        - Create an OrderLine for 2 ENGINEs and add this to a list (Keep a reference to the orderLine so we can get a hold of the OrderLine ID!!!)
        //        - Create an Order using the Order.Builder. Set the orderId and the orderLine list.

        //Act + Assert - verify that the order is not in status PICKED
    }

    @Test
    public void picking_all_lines_results_in_order_in_status_picked() {
        //Arrange - Create a random orderId UUID
        //        - Create an OrderLine for 2 ENGINEs and add this to a list (Keep a reference to the orderLine so we can get a hold of the OrderLine ID!!!)
        //        - Create an Order using the Order.Builder. Set the orderId and the orderLine list.

        //Act - Execute order#pickLine with the orderLine ID and capture the resulting updated Order

        //Assert - Assert that the order is in status PICKED
    }

    @Test
    public void cannot_add_lines_to_an_already_picked_order() {
        //Arrange - Create a random orderId UUID
        //        - Create an OrderLine for 2 ENGINEs and add this to a list (Keep a reference to the orderLine so we can get a hold of the OrderLine ID!!!)
        //        - Create an Order using the Order.Builder. Set the orderId and the orderLine list.
        //        - ADDITIONALLY, pick the 1 orderLine. Capture the resulting updated order

        //Act + Assert - Assert that adding another OrderLine after the order has reached status PICKED, results in an IllegalStateException being thrown.
    }
}
