package com.tvh.bootcamp.testingbootcamp.ordering.infrastructure;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;

public class OrderEntity {

    static OrderEntity fromOrder(Order order) {
        return new OrderEntity();
    }

    Order toOrder() {
        return Order.newOrder();
    }
}
