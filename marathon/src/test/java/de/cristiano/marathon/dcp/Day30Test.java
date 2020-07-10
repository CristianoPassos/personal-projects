package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day30Test {

    private final Day30 day30 = new Day30();

    @Test
    void trappedWater_shouldSucceed() {
        //Given
        int[] elevationMap = {3, 0, 1, 3, 0, 5, 1, 2};

        //When
        final int trappedWater = day30.trappedWater(elevationMap);

        //THen
        assertThat(trappedWater, is(9));
    }
}
