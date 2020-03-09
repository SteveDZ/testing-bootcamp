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
import static com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderLine.forProductAndAmount;

public class Order {
    private UUID orderId;
    private List<OrderLine> orderLines;
    private OrderStatus orderStatus;
    private PriceInEuro orderPrice;

    private Order(UUID orderId, List<OrderLine> orderLines, PriceInEuro orderPrice) {
        this.orderId = orderId;
        this.orderLines = orderLines;
        this.orderStatus = CREATED;
        this.orderPrice = orderPrice;
    }

    public static Order newOrder() {
        return new Order(UUID.randomUUID(), new ArrayList<>(), new PriceInEuro(new BigDecimal(0.00)));
    }

    public static Order newOrderWithLines(List<OrderLine> orderLines) {
        PriceInEuro orderPrice = orderLines.stream()
                .map(OrderLine::getPrice)
                .reduce(new PriceInEuro(new BigDecimal(0.00)), PriceInEuro::add);

        return new Order(UUID.randomUUID(), orderLines, orderPrice);
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
        return this.orderStatus == PICKED;
    }

    public boolean isReadyForShipment() {
        return false;
    }

    public boolean isShipped() {
        return false;
    }

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
    }

    public Order add(Product product, int amount) {
        if (this.orderAlreadyPicked()) {
            throw new IllegalStateException("Can't add lines to an already PICKED Order");
        }

        this.orderLines.add(forProductAndAmount(product, amount));
        this.orderPrice = this.orderPrice.add(product.getPrice().multiply(amount));
        return this;
    }

    private boolean orderAlreadyPicked() {
        return this.orderStatus != CREATED;
    }

    public Order place() {
        this.orderStatus = ORDERED;
        return this;
    }

    public Order pickLine(UUID orderLineId) {
        this.orderLines.stream()
                .filter(orderLine -> orderLineId.equals(orderLine.getId()))
                .findFirst()
                .ifPresent(orderLine -> orderLine.pick());

        if (this.orderLines.stream().allMatch(orderLine -> orderLine.isPicked())) {
            this.orderStatus = PICKED;
        }

        return this;
    }

    public Order ship(Carrier carrier) {
        this.orderStatus = SHIPPED;
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

    public enum OrderStatus {
        CREATED,
        ORDERED,
        PICKED,
        SHIPPED
    }
}
