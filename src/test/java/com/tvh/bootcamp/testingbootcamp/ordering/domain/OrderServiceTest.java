package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void create_Order() {
        //Arrange
        Order order = newOrder();
        when(this.orderRepository.save(order)).thenReturn(order);

        //Act
        Order savedOrder = this.orderService.createOrUpdate(order);

        //Assert
        assertThat(savedOrder).isEqualTo(order);
        verify(this.orderRepository).save(order);
    }

    @Test
    void find_Order() {
        //Arrange
        Order order = newOrder();
        when(this.orderRepository.findById(order.getOrderId())).thenReturn(Optional.of(order));

        //Act
        Order foundOrder = this.orderService.find(order.getOrderId());

        //Assert
        assertThat(foundOrder).isEqualTo(order);
    }

    @Test
    void cannot_find_an_Order_with_unknown_id() {
        //Arrange
        UUID unknownOrderId = UUID.randomUUID();

        //Act
        assertThatThrownBy(() -> this.orderService.find(unknownOrderId)).isInstanceOf(OrderNotFoundException.class);

        //Assert
        verify(this.orderRepository).findById(unknownOrderId);
    }

    //Write unit tests for
    //place
    //pickLine
    //ship
}
