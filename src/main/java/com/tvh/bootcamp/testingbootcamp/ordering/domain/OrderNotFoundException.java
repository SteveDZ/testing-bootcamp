package com.tvh.bootcamp.testingbootcamp.ordering.domain;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
}
