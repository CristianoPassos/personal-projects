package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day25Test {

    private final Day25 day25 = new Day25();

    @Test
    void largesAdjacentSum_shouldSucceed() {
        //Given
        int[] numbers = {2, 14, 6, 2, 5};

        //When
        final int largestSum = day25.largesAdjacentSum(numbers);

        //Then
        assertThat(largestSum).isEqualTo(19);
    }
}
