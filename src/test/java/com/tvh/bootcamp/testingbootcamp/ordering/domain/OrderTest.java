package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.ENGINE;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.SPARK_PLUG;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    //Globally defined Order
    private Order order;

    //Globally defined OrderLine
    private OrderLine orderLine;

    //Globally defined orderId
    private UUID orderId;

    //Implement @BeforeEach setUp() method and setup Order, OrderLine and OrderId (UUID)
    @BeforeEach
    public void setUp() {
        //Create OrderId

        //Create OrderLine for 2 ENGINES

        //Create an OrderLine List with this 1 orderline

        //Create new Order using the Order.Builder. With order id and add the orderlines.
    }

    @Test
    void is_not_Picked() {
        //Arrange completely moved to BeforeEach method
        UUID orderId = UUID.randomUUID();
        OrderLine orderLine = OrderLine.forProductAndAmount(ENGINE, 2);
        List<OrderLine> orderLines = singletonList(orderLine);
        Order order = new Order.Builder()
                .withId(orderId.toString())
                .addOrderLine(orderLines)
                .build();

        //Act + Assert
        assertThat(order.isPicked()).isFalse();
    }

    @Test
    public void picking_all_lines_results_in_order_in_status_picked() {
        //Arrange completely moved to BeforeEach method
        UUID orderId = UUID.randomUUID();
        OrderLine orderLine = OrderLine.forProductAndAmount(ENGINE, 2);
        List<OrderLine> orderLines = singletonList(orderLine);
        Order order = new Order.Builder()
                .withId(orderId.toString())
                .addOrderLine(orderLines)
                .build();

        //Act
        order = order.pickLine(orderLine.getId());

        //Assert
        assertThat(order.isPicked()).isTrue();
    }

    @Test
    public void cannot_add_lines_to_an_already_picked_order() {
        //Arrange
        UUID orderId = UUID.randomUUID();
        OrderLine orderLine = OrderLine.forProductAndAmount(ENGINE, 2);
        List<OrderLine> orderLines = singletonList(orderLine);
        Order order = new Order.Builder()
                .withId(orderId.toString())
                .addOrderLine(orderLines)
                .build();
        final Order pickedOrder = order.pickLine(orderLine.getId());

        //Act + Assert
        assertThatThrownBy(() -> pickedOrder.add(SPARK_PLUG, 1)).isInstanceOf(IllegalStateException.class);
    }
}
