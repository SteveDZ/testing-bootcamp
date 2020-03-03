package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Carrier.DHL;

@Transactional
@Service
public class OrderService implements OrderInteractor {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

//    @Override
//    public void add(Product product, int amount) {
//        Order order = Order.newOrder().add(product, amount);
//        this.orderRepository.save(order);
//    }
//
//    @Override
//    public void add(UUID orderId, Product product, int amount) {
//        Optional<Order> orderById = this.orderRepository.findById(orderId);
//
//        orderById.map(order -> this.orderRepository.save(order.add(product, amount))).orElseThrow();
//    }


    @Override
    public Order createOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order find(UUID orderId) {
        return this.orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %s not found", orderId)));
    }

    @Override
    public void place(UUID orderId) {
        Optional<Order> orderById = this.orderRepository.findById(orderId);

        orderById.map(order -> this.orderRepository.save(order.place()));
    }

    @Override
    public void pickLine(UUID orderId, UUID orderLineId) {
        Optional<Order> orderById = this.orderRepository.findById(orderId);

        orderById.map(order -> this.orderRepository.save(order.pickLine(orderLineId)));
    }

    @Override
    public void ship(UUID orderId) {
        Optional<Order> orderById = this.orderRepository.findById(orderId);

        orderById.map(order -> this.orderRepository.save(order.ship(DHL)));
    }
}
