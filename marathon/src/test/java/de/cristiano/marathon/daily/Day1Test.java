package de.cristiano.marathon.daily;

import de.cristiano.marathon.daily.Day1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day1Test {

    private Day1 day1 = new Day1();

    @Test
    void baseCase_shouldSucceed() {
        //Given
        int[] numbers = {10, 15, 3, 7};

        //When
        final boolean addsUp = day1.solution(numbers, 17);

        //Then
        assertTrue(addsUp);
    }

    @Test
    void doesNotAddsUp_shouldSucceed() {
        //Given
        int[] numbers = {10, -15, 0, 3, 7};

        //When
        final boolean addsUp = day1.solution(numbers, 20);

        //Then
        assertFalse(addsUp);
    }

    @Test
    void negativeAndZeroNumbers_shouldSucceed() {
        //Given
        int[] numbers = {10, -15, 0, 3, 7};

        //When
        final boolean addsUp = day1.solution(numbers, 17);

        //Then
        assertTrue(addsUp);
    }
}