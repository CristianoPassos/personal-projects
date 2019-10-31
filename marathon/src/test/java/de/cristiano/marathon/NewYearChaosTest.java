package de.cristiano.marathon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewYearChaosTest {

    @Test
    void minimumBribes_shouldSucceed() {
        //Given
        final int[] queue = {1, 2, 5, 3, 7, 8, 6, 4};

        //When
        final String bribes = NewYearChaos.minimumBribes(queue);

        //Then
        assertEquals("6", bribes);
    }


    @Test
    void TooChaotic_shouldSucceed() {
        //Given
        final int[] queue = {2, 5, 1, 3, 4};

        //When
        final String bribes = NewYearChaos.minimumBribes(queue);

        //Then
        assertEquals("Too chaotic", bribes);
    }
}