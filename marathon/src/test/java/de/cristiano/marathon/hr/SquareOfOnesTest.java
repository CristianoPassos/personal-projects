package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SquareOfOnesTest {

    private final SquareOfOnes squareOfOnes = new SquareOfOnes();

    @Test
    void maxSquare_shouldSucceed() {
        //Given
        int[][] matrix = {
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
        };

        //When
        final int squareSize = squareOfOnes.maxSquare(matrix);

        //Then
        assertThat(squareSize, is(3));
    }
}
