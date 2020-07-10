package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Test {
    private Day4 day4 = new Day4();

    @Test
    void diagonalCheck_shouldSucceed() {
        //When
        final boolean check = day4.isCheck(3, 0, 2, 1);

        //Then
        assertTrue(check);
    }

    @Test
    void horizontalCheck_shouldSucceed() {
        //When
        final boolean check = day4.isCheck(0, 4, 0, 0);

        //Then
        assertTrue(check);
    }

    @Test
    void noCheck_shouldSucceed() {
        //When
        final boolean check = day4.isCheck(3, 3, 2, 1);

        //Then
        assertFalse(check);
    }

    @Test
    void verticalCheck_shouldSucceed() {
        //When
        final boolean check = day4.isCheck(4, 0, 0, 0);

        //Then
        assertTrue(check);
    }
}
