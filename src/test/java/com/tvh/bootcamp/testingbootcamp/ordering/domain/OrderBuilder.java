package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.ArrayList;
import java.util.List;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrder;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrderWithLines;

public class OrderBuilder {

    private Order order = newOrder();
    private List<OrderLine> orderLines = new ArrayList<>();

    public OrderBuilder() {
    }

    public OrderBuilder withOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
        this.order = newOrderWithLines(orderLines);
        return this;
    }

    public OrderBuilder picked() {
        this.orderLines.forEach(orderLine -> this.order.pickLine(orderLine.getId()));
        return this;
    }

    public Order build() {
        return order;
    }
}
