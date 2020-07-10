package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Array2dTest {

    @Test
    void maxHourglassSum_shouldReturn() {
        final int[][] arr = {
                {-9, -9, -9, 1, 1, 1},
                {0, -9, 0, 4, 3, 2},
                {-9, -9, -9, 1, 2, 3},
                {0, 0, 8, 6, 6, 0},
                {0, 0, 0, -2, 0, 0},
                {0, 0, 1, 2, 4, 0}
        };

        final int maxHourglass = Array2d.hourglassSum(arr);

        assertEquals(28, maxHourglass);
    }

}
