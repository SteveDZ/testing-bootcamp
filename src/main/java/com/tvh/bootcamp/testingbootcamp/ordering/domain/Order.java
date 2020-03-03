package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.util.Strings;

import static com.tvh.bootcamp.testingbootcamp.ordering.domain.OrderLine.forProductAndAmount;

public class Order {
    private UUID orderId;
    private List<OrderLine> orderLines;
    private OrderStatus orderStatus;
    private PriceInEuro orderPrice;

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.orderLines = builder.orderLines;
        this.orderStatus = OrderStatus.CREATED;
        this.orderPrice = builder.orderPrice;
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

    public List<OrderLine> getOrderLines() {
        return this.orderLines;
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

        if (this.orderLines.stream().allMatch(orderLine -> orderLine.isPicked())) {
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

    public static class Builder {
        private UUID orderId;
        private List<OrderLine> orderLines;
        private PriceInEuro orderPrice = new PriceInEuro(new BigDecimal(0.00));

        public Builder() {
        }

        public Builder withId(String id) {
            if (Strings.isNotEmpty(id)) {
                this.orderId = UUID.fromString(id);
            } else {
                this.orderId = UUID.randomUUID();
            }
            return this;
        }

        public Builder addOrderLine(List<OrderLine> orderLines) {
            this.orderLines = Optional.ofNullable(orderLines).orElse(null);
            orderLines.forEach(orderLine -> this.orderPrice = this.orderPrice.add(orderLine.getProduct().getPrice().multiply(orderLine.getAmount())));
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public enum OrderStatus {
        CREATED,
        ORDERED,
        PICKED,
        SHIPPED
    }
}
