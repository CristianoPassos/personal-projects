package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RepeatedStringTest {

    @Test
    public void test_simpleCase() {
        long quantityA = RepeatedString.repeatedString("abc", 6);
        Assertions.assertEquals(2, quantityA);
    }

    @Test
    public void test_NoA() {
        long quantityA = RepeatedString.repeatedString("bcd", 6);
        Assertions.assertEquals(0, quantityA);
    }

    @Test
    public void test_onlyA() {
        long quantityA = RepeatedString.repeatedString("a", 1000000000000L);
        Assertions.assertEquals(1000000000000L, quantityA);
    }
}
