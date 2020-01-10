package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Given a 2D matrix of characters and a target word,
 * write a function that returns whether the word can be found in the matrix by going left-to-right, or up-to-down.
 */
public class Day63 {

    @Test
    void baseCase_shouldSucceed() {
        //Given
        final char[][] board = {
                {'F', 'A', 'C', 'I'},
                {'O', 'B', 'Q', 'P'},
                {'A', 'N', 'O', 'B'},
                {'M', 'A', 'S', 'S'}};

        //When
        final boolean foamExist = exist(board, "FOAM");
        final boolean massExist = exist(board, "MASS");

        //Then
        assertThat(foamExist, is(true));
        assertThat(massExist, is(true));
    }

    public boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (searchWordLeftToRight(board, word, 0, row, column) || searchWordTopBottom(board, word, 0, row, column)) {
                    return true;
                }
            }
        }

        return false;
    }

    boolean searchWordTopBottom(char[][] board, String word, int index, int row, int column) {
        if (index >= word.length()) return true;

        if (isInvalidChar(board, word, index, row, column)) return false;

        return searchWordTopBottom(board, word, index + 1, row + 1, column);
    }

    boolean searchWordLeftToRight(char[][] board, String word, int index, int row, int column) {
        if (index >= word.length()) return true;

        if (isInvalidChar(board, word, index, row, column)) return false;

        return searchWordLeftToRight(board, word, index + 1, row, column + 1);
    }

    private boolean isInvalidChar(char[][] board, String word, int index, int row, int column) {
        return row < 0 || row >= board.length || column < 0 || column >= board[row].length || board[row][column] != word.charAt(index);
    }
}
