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

    private Order order;

    private OrderLine orderLine;

    private UUID orderId;

    @BeforeEach
    public void setUp() {
        this.orderId = UUID.randomUUID();
        this.orderLine = OrderLine.forProductAndAmount(ENGINE, 2);
        List<OrderLine> orderLines = singletonList(orderLine);
        this.order = new Order.Builder()
                .withId(orderId.toString())
                .addOrderLine(orderLines)
                .build();
    }

    @Test
    void is_not_Picked() {
        //Arrange completely moved to BeforeEach method

        //Act + Assert
        assertThat(order.isPicked()).isFalse();
    }

    @Test
    public void picking_all_lines_results_in_order_in_status_picked() {
        //Arrange completely moved to BeforeEach method

        //Act
        this.order = this.order.pickLine(this.orderLine.getId());

        //Assert
        assertThat(this.order.isPicked()).isTrue();
    }

    @Test
    public void cannot_add_lines_to_an_already_picked_order() {
        //Arrange
        final Order pickedOrder = this.order.pickLine(this.orderLine.getId());

        //Act + Assert
        assertThatThrownBy(() -> pickedOrder.add(SPARK_PLUG, 1)).isInstanceOf(IllegalStateException.class);
    }
}
