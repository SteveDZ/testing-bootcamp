package com.tvh.bootcamp.testingbootcamp.samples;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongStringTest {

    @Test
    public void isLongString() {
        LongString longString = new LongString();

        boolean nope = longString.isLongString("Nope");

        assertEquals(false, nope);
    }

    @Test
    public void isLongStringTooLong() {
        LongString longString = new LongString();

        boolean nope = longString.isLongString("Nooooope");

        assertEquals(true, nope);
    }

    @Test
    public void assertionLessTest() {
        LongString longString = new LongString();

        longString.isLongString("short");
        longString.isLongString("looooooong");
    }

}
