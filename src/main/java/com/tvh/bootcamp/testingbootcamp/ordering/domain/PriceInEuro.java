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

    public PriceInEuro add(PriceInEuro amountToAdd) {
        return new PriceInEuro(this.price.add(amountToAdd.getPrice()));
    }

    public PriceInEuro subtract(PriceInEuro amountToSubtract) {
        return new PriceInEuro(this.price.subtract(amountToSubtract.getPrice()));
    }

    public PriceInEuro multiply(int multiplicand) {
        return new PriceInEuro(this.price.multiply(new BigDecimal(multiplicand)));
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

    @Override
    public String toString() {
        return "PriceInEuro{" +
                "price=" + price +
                '}';
    }
}
