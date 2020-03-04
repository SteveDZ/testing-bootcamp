package com.tvh.bootcamp.testingbootcamp.ordering.domain;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceInEuroTest {

    @Test
    void add() {
        //Arrange - Create tenEuro priceInEuro object
        PriceInEuro tenEuro = new PriceInEuro(new BigDecimal(10.00));

        //Act - add twentyEuro to tenEuro. Capture the result
        PriceInEuro thirtyEuro = tenEuro.add(new PriceInEuro(new BigDecimal(20.00)));

        //Assert - assert that the result is thirty euro
        assertThat(thirtyEuro.getPrice()).isEqualTo(new BigDecimal(30.00));
    }

    @Test
    void subtract() {
        //Arrange - Create twentyEuro priceInEuro object
        PriceInEuro twentyEuro = new PriceInEuro(new BigDecimal(20.00));

        //Act - subtract tenEuro. Capture the result
        PriceInEuro tenEuro = twentyEuro.subtract(new PriceInEuro(new BigDecimal(10.00)));

        //Assert - assert that the result is ten euro
        assertThat(tenEuro.getPrice()).isEqualTo(new BigDecimal(10.00));
    }

    @Test
    void multiply() {
        //Arrange - Create twentyEuro priceInEuro object
        PriceInEuro twentyEuro = new PriceInEuro(new BigDecimal(20.00));

        //Act - multiply by 10. Capture the result
        PriceInEuro twoHundredEuro = twentyEuro.multiply(10);

        //Assert - assert that result is two hundred euro
        assertThat(twoHundredEuro.getPrice()).isEqualTo(new BigDecimal(200.00));
    }
}
