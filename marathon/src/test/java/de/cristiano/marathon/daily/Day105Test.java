package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day105Test {

    private Day105 day105 = new Day105();

    @Test
    void solveMaze_shouldSucceed() {
        //Given
        final int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        //When
        final int[][] path = day105.solveMaze(maze, 0, 0, 3, 3);

        //Then
        final int pathExpected[][] = {
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 1, 1}
        };

        assertThat(path, arrayContaining(pathExpected));


    }
}