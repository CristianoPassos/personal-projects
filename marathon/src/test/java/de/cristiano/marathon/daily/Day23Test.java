package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    private final Day23 day23 = new Day23();

    @Test
    void decodeTest_shouldSucceed() {
        //When
        final int numWays = day23.numWays("1202137");
        final int numWays2 = day23.numWays("111");

        //Then
        assertThat(numWays, is(3));
        assertThat(numWays2, is(3));
    }
}