package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceInEuroTest {

    @Test
    void add() {
        PriceInEuro tenEuro = new PriceInEuro(new BigDecimal(10.00));

        PriceInEuro thirtyEuro = tenEuro.add(new PriceInEuro(new BigDecimal(20.00)));

        assertThat(thirtyEuro.getPrice()).isEqualTo(new BigDecimal(30.00));
    }

    @Test
    void subtract() {
        PriceInEuro twentyEuro = new PriceInEuro(new BigDecimal(20.00));

        PriceInEuro tenEuro = twentyEuro.subtract(new PriceInEuro(new BigDecimal(10.00)));

        assertThat(tenEuro.getPrice()).isEqualTo(new BigDecimal(10.00));
    }

    @Test
    void multiply() {
        PriceInEuro twentyEuro = new PriceInEuro(new BigDecimal(20.00));

        PriceInEuro twoHundredEuro = twentyEuro.multiply(10);

        assertThat(twoHundredEuro.getPrice()).isEqualTo(new BigDecimal(200.00));
    }
}
