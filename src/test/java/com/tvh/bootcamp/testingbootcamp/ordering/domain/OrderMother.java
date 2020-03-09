package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;

public final class OrderMother {

    private OrderMother() {
    }

    //Implement the anOrderWithOneLine method
    static Order anOrderWithOneLine(OrderLine orderLine) {
        UUID orderId = UUID.randomUUID();
        List<OrderLine> orderLines = singletonList(orderLine);
        return new Order.Builder()
                .withId(orderId.toString())
                .addOrderLine(orderLines)
                .build();
    }

}
