package de.cristiano.marathon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Day5Test {

    private Day5 day5 = new Day5();

    @Test
    void merge_shouldSucceed() {
        //Given
        int[] arrayA = {0, 3, 4, 31};
        int[] arrayB = {3, 4, 6, 30};

        //When
        final int[] solution = day5.solution(arrayA, arrayB);

        //Then
        int[] expected = {0, 3, 3, 4, 4, 6, 30, 31};
        assertArrayEquals(expected, solution);
    }

}