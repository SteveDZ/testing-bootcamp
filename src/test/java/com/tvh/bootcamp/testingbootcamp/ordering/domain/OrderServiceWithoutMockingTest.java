package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tvh.bootcamp.testingbootcamp.ordering.infrastructure.InMemoryOrderRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderServiceWithoutMockingTest {

    private OrderService orderService;

    //Implement @BeforeEach setUp() method that creates an OrderService
    @BeforeEach
    public void setUp() {

    }

    @Test
    void create_Order() {
        //Remove the OrderService creation logic
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
        //Remove the OrderService creation logic
        //Arrange
        this.orderService = new OrderService(new InMemoryOrderRepository());
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
        //Remove the OrderService creation logic
        //Arrange
        this.orderService = new OrderService(new InMemoryOrderRepository());
        UUID unknownOrderId = UUID.randomUUID();

        //Act + Assert
        assertThatThrownBy(() -> orderService.find(unknownOrderId)).isInstanceOf(OrderNotFoundException.class);
    }
}
