package com.tvh.bootcamp.testingbootcamp.ordering.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public class OrderLineResource {

    private String product;
    private int amount;

    @JsonCreator
    OrderLineResource(@JsonProperty String product, @JsonProperty int amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return this.product;
    }

    public int getAmount() {
        return this.amount;
    }
}
