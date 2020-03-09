package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.ArrayList;
import java.util.List;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrderWithLines;

public final class OrderMother {

    private OrderMother() {
    }

    //Implement the anOrderWithOneLine method
    static Order anOrderWithOneLine(OrderLine orderLine) {
        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(orderLine);
        return newOrderWithLines(orderLines);
    }

}
