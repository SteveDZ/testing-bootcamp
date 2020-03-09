package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.List;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrder;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrderWithLines;

public class OrderBuilder {

    private Order order = newOrder();

    public OrderBuilder() {
    }

    public OrderBuilder withOrderLines(List<OrderLine> orderLines) {
        this.order = newOrderWithLines(orderLines);
        return this;
    }

    public Order build() {
        return order;
    }
}
