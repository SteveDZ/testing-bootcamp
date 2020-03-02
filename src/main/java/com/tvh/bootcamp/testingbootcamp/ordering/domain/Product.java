package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;

public enum Product {
    ENGINE(new PriceInEuro(new BigDecimal(10_000.00))),
    TRANSMISSION(new PriceInEuro(new BigDecimal(2_000.00))),
    SPARK_PLUG(new PriceInEuro(new BigDecimal(500.00))),
    TYRE(new PriceInEuro(new BigDecimal(1_000.00)));

    private PriceInEuro price;

    PriceInEuro getPrice() {
        return this.price;
    }

    Product(PriceInEuro price) {
        this.price = price;
    }
}
