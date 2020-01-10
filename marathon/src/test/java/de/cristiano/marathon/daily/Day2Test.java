package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class Day2Test {

    private Day2 day2 = new Day2();

    @Test
    void complexArray_shouldSucceed() {
        //given

        int[] numbers = {1, 2, 3, 4, 5};
        //when


        final int[] solution = day2.solution(numbers);
        //then

        final int[] expected = {120, 60, 40, 30, 24};
        assertArrayEquals(expected, solution);
    }

    @Test
    void simpleArray_shouldSucceed() {
        //given

        int[] numbers = {3, 2, 1};
        //when


        final int[] solution = day2.solution(numbers);
        //then

        final int[] expected = {2, 3, 6};
        assertArrayEquals(expected, solution);
    }

    @Test
    void zeroArray_shouldSucceed() {
        //given

        int[] numbers = {0, 2, 3, 4, 5};
        //when


        final int[] solution = day2.solution(numbers);
        //then

        final int[] expected = {120, 0, 0, 0, 0};
        assertArrayEquals(expected, solution);
    }


    @Test
    void zerosArray_shouldSucceed() {
        //given

        int[] numbers = {0, 0, 3, 4, 5};
        //when


        final int[] solution = day2.solution(numbers);
        //then

        final int[] expected = {0, 0, 0, 0, 0};
        assertArrayEquals(expected, solution);
    }
}