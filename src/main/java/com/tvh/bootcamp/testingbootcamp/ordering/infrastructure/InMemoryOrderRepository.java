package com.tvh.bootcamp.testingbootcamp.ordering.infrastructure;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderRepository;

//@Component
public class InMemoryOrderRepository implements OrderRepository {

    private Map<UUID, OrderEntity> orders;

    @Override
    public Order save(Order order) {
        orders.put(order.getOrderId(), OrderEntity.fromOrder(order));
        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        Optional<OrderEntity> orderEntity =
                orders.entrySet()
                        .stream()
                        .filter(orderEntry -> id.equals(orderEntry.getKey()))
                        .map(entry -> entry.getValue())
                        .findFirst();

        return orderEntity.map(OrderEntity::toOrder);
    }
}
