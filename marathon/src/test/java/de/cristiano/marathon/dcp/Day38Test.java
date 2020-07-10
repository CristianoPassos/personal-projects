package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day38Test {

    private Day38 day38 = new Day38();


    @Test
    void possibleQueenArrangements_shouldSucceed() {
        //When
        final int board2x2 = day38.possibleQueenArrangements(2);
        final int board3x3 = day38.possibleQueenArrangements(3);
        final int board10x10 = day38.possibleQueenArrangements(10);

        //Then
        assertThat(board2x2, is(0));
        assertThat(board3x3, is(8));
        assertThat(board10x10, is(3480));
    }
}
