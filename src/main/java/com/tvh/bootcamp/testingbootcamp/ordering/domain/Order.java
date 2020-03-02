package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.OrderStatus.CREATED;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.OrderStatus.ORDERED;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.OrderStatus.PICKED;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.Order.OrderStatus.SHIPPED;
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderLine.*;

public class Order {
    private UUID orderId;
    private List<OrderLine> orderLines;
    private OrderStatus orderStatus;
    private PriceInEuro orderPrice;

    private Order() {
        this.orderId = UUID.randomUUID();
        this.orderLines = new ArrayList<>();
        this.orderStatus = OrderStatus.CREATED;
        this.orderPrice = new PriceInEuro(new BigDecimal(0.00));
    }

    public UUID getOrderId() {
        return this.orderId;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }

    public PriceInEuro getOrderPrice() {
        return this.orderPrice;
    }

    public boolean isPicked() {
        return false;
    }

    public boolean isReadyForShipment() {
        return false;
    }

    public boolean isShipped() {
        return false;
    }

    public static Order newOrder() {
        return new Order();
    }

    public Order add(Product product, int amount) {
        //When order is beyond created, throw exception
        this.orderLines.add(forProductAndAmount(product, amount));
        return this;
    }

    public Order place() {
        this.orderStatus = OrderStatus.ORDERED;
        //return OrderPlaced Event;
        return this;
    }

    public Order pickLine(UUID orderLineId) {
        this.orderLines.stream()
                .filter(orderLine -> orderLineId.equals(orderLine.getId()))
                .findFirst()
                .ifPresent(orderLine -> orderLine.pick());

        if(this.orderLines.stream().allMatch(orderLine -> orderLine.isPicked())) {
            this.orderStatus = OrderStatus.PICKED;
        }

        return this;
    }

    public Order ship(Carrier carrier) {
        this.orderStatus = OrderStatus.SHIPPED;
        //return OrderShipped Event;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return orderId.equals(order.orderId) &&
                orderLines.equals(order.orderLines) &&
                orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderLines, orderStatus);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderLines=" + orderLines +
                ", orderStatus=" + orderStatus +
                '}';
    }

    static enum OrderStatus {
        CREATED,
        ORDERED,
        PICKED,
        SHIPPED
    }
}
