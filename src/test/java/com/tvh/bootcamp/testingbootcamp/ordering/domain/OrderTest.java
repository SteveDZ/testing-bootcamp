package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import org.junit.jupiter.api.Test;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.newOrder;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.ENGINE;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.SPARK_PLUG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    @Test
    void is_not_Picked() {
        //Arrange
        Order order = newOrder();
        order.add(ENGINE, 2);

        //Act + Assert
        assertThat(order.isPicked()).isFalse();
    }

    @Test
    public void picking_all_lines_results_in_order_in_status_picked() {
        //Arrange
        Order order = newOrder();
        order.add(ENGINE, 2);
        OrderLine orderLine = order.getOrderLines().get(0);

        //Act
        order = order.pickLine(orderLine.getId());

        //Assert
        assertThat(order.isPicked()).isTrue();
    }

    @Test
    public void cannot_add_lines_to_an_already_picked_order() {
        //Arrange
        Order order = newOrder();
        order.add(ENGINE, 2);
        OrderLine orderLine = order.getOrderLines().get(0);
        final Order pickedOrder = order.pickLine(orderLine.getId());

        //Act + Assert
        assertThatThrownBy(() -> pickedOrder.add(SPARK_PLUG, 1)).isInstanceOf(IllegalStateException.class);
    }
}
