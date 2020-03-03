package com.tvh.bootcamp.testingbootcamp.ordering.infrastructure;

import java.util.UUID;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;

public class OrderEntity {

    private UUID orderId;

    static OrderEntity fromOrder(Order order) {
        return new OrderEntity();
    }

    Order toOrder() {
        return new Order.Builder().build();
    }
}
