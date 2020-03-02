package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class PriceInEuro {
    private BigDecimal price;

    public PriceInEuro(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PriceInEuro that = (PriceInEuro) o;
        return price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }
}
