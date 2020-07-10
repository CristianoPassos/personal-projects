package de.cristiano.marathon.dcp;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * You have an N by N board. Write a function that, given N,
 * returns the number of possible arrangements of the board where N queens can be placed on the board without threatening each other,
 * i.e. no two queens share the same row, column, or diagonal.
 */
public class Day38 {

    int possibleQueenArrangements(int boarSize) {
        int ways = 0;

        final Set<Coordinates> searched = new HashSet<>();

        for (int row = 0; row < boarSize; row++) {
            for (int column = 0; column < boarSize; column++) {
                final Coordinates one = new Coordinates(row, column);

                ways += possibleQueenArrangements(boarSize, one, searched);

                searched.add(one);
            }
        }

        return ways;
    }

    private int possibleQueenArrangements(int boarSize, Coordinates one, Set<Coordinates> searched) {
        int ways = 0;

        for (int row = 0; row < boarSize; row++) {
            for (int column = 0; column < boarSize; column++) {
                final Coordinates two = new Coordinates(row, column);

                if (!searched.contains(two) && !one.equals(two) &&
                        !isSameColumn(one, two) && !isSameRow(one, two) && !isSameDiagonal(one, two)) {
                    ways++;
                }
            }
        }

        return ways;
    }

    private boolean isSameRow(Coordinates one, Coordinates two) {
        return one.row == two.row;
    }

    private boolean isSameColumn(Coordinates one, Coordinates two) {
        return one.column == two.column;
    }

    private boolean isSameDiagonal(Coordinates one, Coordinates two) {
        return Math.abs(one.column - two.column) == Math.abs(one.row - two.row);
    }

    private static class Coordinates {
        private int row;
        private int column;

        public Coordinates(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return row == that.row &&
                    column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
