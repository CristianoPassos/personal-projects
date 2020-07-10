package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day39Test {

    private final Day39 day39 = new Day39();

    @Test
    void decodeTest_shouldSucceed() {
        //When
        final int numWays = day39.numWays("1202137");
        final int numWays2 = day39.numWays("111");

        //Then
        assertThat(numWays, is(3));
        assertThat(numWays2, is(3));
    }
}
