package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.tvh.bootcamp.testingbootcamp.ordering.infrastructure.InMemoryOrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderServiceWithoutMockingTest {

    @Test
    void create_Order() {
        //Arrange
        OrderService orderService = new OrderService(new InMemoryOrderRepository());
        Order order = new Order.Builder().build();

        //Act
        Order savedOrder = orderService.createOrUpdate(order);

        //Assert
        assertThat(savedOrder).isEqualTo(order);
    }

    @Test
    void find_Order() {
        //Arrange
        OrderService orderService = new OrderService(new InMemoryOrderRepository());
        UUID orderId = UUID.randomUUID();
        Order order = new Order.Builder().withId(orderId.toString()).build();
        orderService.createOrUpdate(order);

        //Act
        Order foundOrder = orderService.find(orderId);

        //Assert
        assertThat(foundOrder).isEqualTo(order);
    }

    @Test
    void cannot_find_an_Order_with_unknown_id() {
        //Arrange
        OrderService orderService = new OrderService(new InMemoryOrderRepository());
        UUID unknownOrderId = UUID.randomUUID();

        //Act + Assert
        assertThatThrownBy(() -> orderService.find(unknownOrderId)).isInstanceOf(OrderNotFoundException.class);
    }
}
