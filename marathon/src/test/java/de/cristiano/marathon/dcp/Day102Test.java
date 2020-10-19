package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day102Test {

    private final Day102 day102 = new Day102();


    @Test
    void maxContiguousSum_shouldSucceed() {
        //Given
        int[] testArray1 = {34, -50, 42, 14, -5, 86};
        int[] testArray2 = {-5, -1, -8, -9};

        //When
        final int testArray1_result = day102.maxContiguousSum(testArray1);
        final int testArray2_result = day102.maxContiguousSum(testArray2);

        //Then
        assertThat(testArray1_result).isEqualTo(137);
        assertThat(testArray2_result).isEqualTo(0);
    }
}
