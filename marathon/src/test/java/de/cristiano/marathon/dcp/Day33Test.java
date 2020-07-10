package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day33Test {

    private final Day33 day33 = new Day33();

    @Test
    void canBeColored_shouldSucceed() {
        //Given
        int[][] canBeColoredAdjacencyMatrix = {
                {0, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 1},
                {1, 0, 1, 0}
        };

        int[][] cannotBeColoredAdjacencyMatrix = {
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 1},
                {1, 1, 1, 0}
        };

        //When
        final boolean canBeColored = day33.canBeColored(canBeColoredAdjacencyMatrix, 2);
        final boolean canNotBeColored = day33.canBeColored(cannotBeColoredAdjacencyMatrix, 2);

        assertThat(canBeColored, is(true));
        assertThat(canNotBeColored, is(false));
    }

}
