package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day61Test {

    private final Day61 day61 = new Day61();

    @Test
    void
    pow_shouldSucceed() {
        //When
        final float pow = day61.pow(2, 10);
        final float powNegative = day61.pow(2, -10);

        //Then
        assertThat(pow, is(1024F));
        assertThat(powNegative, is(0.0009765625F));
    }
}
