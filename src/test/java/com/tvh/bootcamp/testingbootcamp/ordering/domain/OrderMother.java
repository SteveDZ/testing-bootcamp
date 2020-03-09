package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.List;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrderWithLines;
import static java.util.Collections.singletonList;

public final class OrderMother {

    private OrderMother() {
    }

    //Implement the anOrderWithOneLine method
    static Order anOrderWithOneLine(OrderLine orderLine) {
        List<OrderLine> orderLines = singletonList(orderLine);
        return newOrderWithLines(orderLines);
    }

}
