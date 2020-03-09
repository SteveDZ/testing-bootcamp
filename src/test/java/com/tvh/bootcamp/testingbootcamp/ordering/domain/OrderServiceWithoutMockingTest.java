package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tvh.bootcamp.testingbootcamp.ordering.infrastructure.InMemoryOrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderServiceWithoutMockingTest {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        this.orderService = new OrderService(new InMemoryOrderRepository());
    }

    @Test
    void create_Order() {
        //Arrange
        Order order = Order.newOrder();

        //Act
        Order savedOrder = orderService.createOrUpdate(order);

        //Assert
        assertThat(savedOrder).isEqualTo(order);
    }

    @Test
    void find_Order() {
        //Arrange
        Order order = Order.newOrder();
        UUID orderId = order.getOrderId();
        orderService.createOrUpdate(order);

        //Act
        Order foundOrder = orderService.find(orderId);

        //Assert
        assertThat(foundOrder).isEqualTo(order);
    }

    @Test
    void cannot_find_an_Order_with_unknown_id() {
        //Arrange
        UUID unknownOrderId = UUID.randomUUID();

        //Act + Assert
        assertThatThrownBy(() -> orderService.find(unknownOrderId)).isInstanceOf(OrderNotFoundException.class);
    }
}
