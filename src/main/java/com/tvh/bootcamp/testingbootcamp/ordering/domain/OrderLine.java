package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.util.Objects;
import java.util.UUID;

public class OrderLine {
    private UUID id;
    private Product product;
    private int amount;
    private OrderLineStatus status;

    private OrderLine(Product product, int amount) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.amount = amount;
        this.status = OrderLineStatus.CREATED;
    }

    public UUID getId() {
        return this.id;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean isPicked() {
        return this.status == OrderLineStatus.PICKED;
    }

    public PriceInEuro getPrice() {
        return this.product.getPrice().multiply(this.amount);
    }

    public static OrderLine forProductAndAmount(Product product, int amount) {
//        validation on negative amounts;
        return new OrderLine(product, amount);
    }

    void pick() {
        this.status = OrderLineStatus.PICKED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderLine orderLine = (OrderLine) o;
        return amount == orderLine.amount &&
                id.equals(orderLine.id) &&
                product == orderLine.product &&
                status == orderLine.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, amount, status);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", product=" + product +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }

    enum OrderLineStatus {
        CREATED,
        PICKED,
    }
}
