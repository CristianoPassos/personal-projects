package de.cristiano.marathon.daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * There is an N by M matrix of zeroes. Given N and M,
 * write a function to count the number of ways of starting at the top-left corner and getting to the bottom-right corner.
 * You can only move right or down.
 */
public class Day62 {

    int pathsTowardsGoal(int rows, int columns) {
        final Point startPosition = new Point(rows - 1, 0);
        final Point goal = new Point(0, columns - 1);
        final Map<Point, Integer> cache = new HashMap<>();

        return calculatePaths(rows, columns, startPosition, goal, cache);
    }


    private int calculatePaths(int rows, int columns, Point position, Point goal, Map<Point, Integer> cache) {
        if (position.equals(goal)) {
            return 1;
        }

        if (position.row < 0 || position.column < 0 || position.row >= rows || position.column >= columns) {
            return 0;
        }

        if (!cache.containsKey(position.down())) {
            final int ways = calculatePaths(rows, columns, position.down(), goal, cache);
            cache.put(position.down(), ways);
        }

        if (!cache.containsKey(position.right())) {
            final int ways = calculatePaths(rows, columns, position.right(), goal, cache);
            cache.put(position.right(), ways);
        }

        return cache.get(position.down()) + cache.get(position.right());
    }

    static class Point {
        final int row;
        final int column;

        public Point(int row, int column) {
            this.row = row;
            this.column = column;
        }

        Point down() {
            return new Point(this.row - 1, column);
        }

        Point right() {
            return new Point(this.row, column + 1);
        }

        @Override
        public String toString() {
            return this.row + "," + this.column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return this.row == point.row &&
                    this.column == point.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.column);
        }
    }
}
