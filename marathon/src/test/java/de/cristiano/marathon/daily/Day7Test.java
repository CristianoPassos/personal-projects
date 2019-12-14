package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day7Test {

    private final Day7 day7 = new Day7();

    @Test
    void maxSubArray_shouldSucceed() {
        //Given
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        //When
        final int maxSum = day7.maxSubArray(array);

        //Then
        assertThat(maxSum, is(6));
    }
}