package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.UUID;

public interface OrderInteractor {

    Order createOrUpdate(Order order);

    Order find(UUID orderId);

    void place(UUID orderId);

    void pickLine(UUID orderId, UUID orderLineId);

    void ship(UUID orderId);
}
