package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    private final Day20 day20 = new Day20();

    @Test
    void climbWays_shouldSucceed() {
        //When
        final int climbWays = day20.climbWays(4, 1, 2);

        //Then
        assertThat(climbWays, is(5));
    }
}
