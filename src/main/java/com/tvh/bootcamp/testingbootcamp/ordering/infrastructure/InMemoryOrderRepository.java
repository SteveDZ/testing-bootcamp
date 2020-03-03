package com.tvh.bootcamp.testingbootcamp.ordering.infrastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderRepository;

import org.springframework.stereotype.Component;

@Component
public class InMemoryOrderRepository implements OrderRepository {

    private Map<UUID, Order> orders = new HashMap<>();

    @Override
    public Order save(Order order) {
        orders.put(order.getOrderId(), order);

        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }
}
