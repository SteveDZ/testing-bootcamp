package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.UUID;

public interface OrderInteractor {

    void add(Product product, int amount);

    void add(UUID orderId, Product product, int amount);

    void place(UUID orderId);

    void pickLine(UUID orderId, UUID orderLineId);

    void ship(UUID orderId);
}
