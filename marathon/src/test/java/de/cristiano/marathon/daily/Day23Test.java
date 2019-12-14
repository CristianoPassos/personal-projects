package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    private Day23 day23 = new Day23();

    @Test
    void possiblePath_shouldSucceed() {
        //Given
        final boolean[][] board = {
                {false, false, false, false},
                {false, false, false, false},
                {true, true, false, true},
                {false, false, false, false}
        };

        final Day23.Coordinate startPoint = new Day23.Coordinate(3, 0);
        final Day23.Coordinate goal = new Day23.Coordinate(0, 0);

        //When
        final Integer minPath = day23.minPath(board, startPoint, goal);

        //Then
        assertThat(minPath, is(7));
    }


    @Test
    void impossiblePath_shouldSucceed() {
        //Given
        final boolean[][] board = {
                {false, false, false, false},
                {false, false, false, false},
                {true, true, true, true},
                {false, false, false, false}
        };

        final Day23.Coordinate startPoint = new Day23.Coordinate(3, 0);
        final Day23.Coordinate goal = new Day23.Coordinate(0, 0);

        //When
        final Integer minPath = day23.minPath(board, startPoint, goal);

        //Then
        assertThat(minPath, is(nullValue()));
    }
}