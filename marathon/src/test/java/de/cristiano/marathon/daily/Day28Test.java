package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day28Test {

    private final Day28 day28 = new Day28();


    @Test
    void maxContiguousSum_shouldSucceed() {
        //Given
        int[] testArray1 = {34, -50, 42, 14, -5, 86};
        int[] testArray2 = {-5, -1, -8, -9};

        //When
        final int testArray1_result = day28.maxContiguousSum(testArray1);
        final int testArray2_result = day28.maxContiguousSum(testArray2);

        //Then
        assertThat(testArray1_result, is(137));
        assertThat(testArray2_result, is(0));
    }
}