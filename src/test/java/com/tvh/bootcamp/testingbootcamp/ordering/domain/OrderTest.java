package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderMother.anOrderWithOneLine;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.ENGINE;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Product.SPARK_PLUG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {

    private Order order;

    private OrderLine orderLine;

    @BeforeEach
    public void setUp() {
        //Create OrderLine for ENGINE and amount 2
        this.orderLine = OrderLine.forProductAndAmount(ENGINE, 2);
        //Create Order using the OrderMother#anOrderWithOneLine method
        this.order = anOrderWithOneLine(this.orderLine);
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

    @Test
    public void adding_lines_recalculate_order_price() {
        Order newOrder = this.order.add(SPARK_PLUG, 2);

        assertThat(newOrder.getOrderPrice()).isEqualTo(new PriceInEuro(new BigDecimal(21_000.00)));
    }
}
