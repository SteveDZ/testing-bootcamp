package com.tvh.bootcamp.testingbootcamp.ordering.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.tvh.bootcamp.testingbootcamp.ordering.domain.Order;
import com.tvh.bootcamp.testingbootcamp.ordering.domain.Product;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.stream.Collectors.toList;

@JsonInclude(NON_NULL)
public class OrderResource {

    private String id;
    private List<OrderLineResource> orderLines;
    private BigDecimal orderPrice;
    private String orderStatus;

    @JsonCreator
    public OrderResource(@JsonProperty String id, @JsonProperty List<OrderLineResource> orderLines) {
        this.id = Optional.ofNullable(id).orElse(null);
        this.orderLines = Optional.ofNullable(orderLines).orElse(new ArrayList<>());
    }

    private OrderResource(String id, List<OrderLineResource> orderLines, BigDecimal orderPrice, String orderStatus) {
        this(id, orderLines);
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return this.id;
    }

    public List<OrderLineResource> getOrderLines() {
        return this.orderLines;
    }

    public BigDecimal getOrderPrice() {
        return this.orderPrice;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    Order toOrder() {
        Order order = Order.newOrder();
        this.orderLines.forEach(orderLineResource -> order.add(Product.valueOf(orderLineResource.getProduct()), orderLineResource.getAmount()));

        return order;
    }

    static OrderResource fromOrder(Order order) {
        List<OrderLineResource> orderLineResources =
                order.getOrderLines().stream()
                        .map(orderLine -> new OrderLineResource(orderLine.getProduct().toString(), orderLine.getAmount()))
                        .collect(toList());

        return new OrderResource(order.getOrderId().toString(), orderLineResources, order.getOrderPrice().getPrice(), order.getOrderStatus().toString());
    }
}
