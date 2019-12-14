package de.cristiano.marathon.daily;

import java.util.Objects;

/**
 * The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
 */
public class Day37 {

    int[][] distributeQueensOverBoard(int queens) {
        final Coordinate[] queensPosition = new Coordinate[queens];

        if (addQueen(queensPosition, 0)) {
            final int[][] board = new int[queens][queens];

            for (Coordinate coordinate : queensPosition) {
                board[coordinate.row][coordinate.column] = 1;
            }

            return board;
        }

        return null;
    }

    private boolean addQueen(Coordinate[] queensPosition, int queens) {

        if (!isValid(queensPosition)) {
            return false;
        }

        if (queens == queensPosition.length) {
            return true;
        }

        for (int startRow = 0; startRow < queensPosition.length; startRow++) {
            for (int startColumn = 0; startColumn < queensPosition.length; startColumn++) {
                queensPosition[queens] = new Coordinate(startRow, startColumn);

                if (addQueen(queensPosition, queens + 1)) {
                    return true;
                }
            }
        }

        queensPosition[queens] = null;

        return false;
    }


    private boolean isValid(Coordinate[] queens) {
        for (int queen1 = 0; queen1 < queens.length; queen1++) {
            for (int queen2 = queen1 + 1; queen2 < queens.length; queen2++) {
                if (isSameRow(queens[queen1], queens[queen2])
                        || isSameDiagonal(queens[queen1], queens[queen2])
                        || isSameColumn(queens[queen1], queens[queen2])) {
                    return false;
                }
            }
        }

        return true;
    }


    private boolean isSameRow(Coordinate one, Coordinate two) {
        if (Objects.isNull(one) || Objects.isNull(two)) {
            return false;
        }

        return one.row == two.row;
    }

    private boolean isSameColumn(Coordinate one, Coordinate two) {
        if (Objects.isNull(one) || Objects.isNull(two)) {
            return false;
        }

        return one.column == two.column;
    }

    private boolean isSameDiagonal(Coordinate one, Coordinate two) {
        if (Objects.isNull(one) || Objects.isNull(two)) {
            return false;
        }

        return Math.abs(one.column - two.column) == Math.abs(one.row - two.row);
    }

    private static class Coordinate {
        private int row;
        private int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return row == that.row &&
                    column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}