package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day19Test {

    private final Day19 day19 = new Day19();

    @Test
    void minCost_shouldSucceed() {
        //Given
        int[][] paintCosts = {
                {10, 15, 50},
                {30, 35, 60},
                {50, 5, 32},
                {60, 10, 27}
        };

        //When
        final int totalPaintCost = day19.minCost(paintCosts);

        //Then
        assertThat(totalPaintCost, is(42));
    }
}
