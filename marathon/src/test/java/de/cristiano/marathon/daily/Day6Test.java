package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Day6Test {

    private Day6 day6 = new Day6();

    @Test
    void twoSum_shouldSucceed() {
        //Given
        final int[] array = {2, 7, 11, 15};

        //When
        final int[] actual = day6.twoSum(array, 9);

        //Then
        final int[] expected = {0, 1};

        assertArrayEquals(expected, actual);
    }
}