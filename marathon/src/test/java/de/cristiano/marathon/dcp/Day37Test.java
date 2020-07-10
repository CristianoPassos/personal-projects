package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class Day37Test {

    private final Day37 day37 = new Day37();

    @Test
    void distributeQueensOverBoard_shouldSucceed() {
        //Given
        final int[][] board = day37.distributeQueensOverBoard(4);

        //Then
        assertThat(board, is(not(nullValue())));
    }
}
