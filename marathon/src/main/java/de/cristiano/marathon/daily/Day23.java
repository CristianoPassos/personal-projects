package de.cristiano.marathon.daily;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Comparator.*;

public class Day23 {

    Integer minPath(boolean[][] matrix, Coordinate startPoint, Coordinate goal) {
        final List<Coordinate> path = new ArrayList<>();
        final Map<Coordinate, Integer> cache = new HashMap<>();

        return minPath(matrix, startPoint, goal, path, cache);
    }

    private Integer minPath(boolean[][] matrix, Coordinate startPoint, Coordinate goal, List<Coordinate> path, Map<Coordinate, Integer> cache) {
        final List<Coordinate> currentPath = new ArrayList<>(path);

        if (startPoint.row < 0 || startPoint.column < 0 || startPoint.column >= matrix.length || startPoint.row >= matrix[0].length) {
            return null;
        }

        if (currentPath.contains(startPoint)) {
            return null;
        }

        if (matrix[startPoint.row][startPoint.column]) {
            return null;
        }

        if (startPoint.equals(goal)) {
            return 0;
        }

        path.add(startPoint);

        if (!cache.containsKey(startPoint)) {
            final Integer minDistance = calculatePath(matrix, startPoint, goal, path, cache);

            cache.put(startPoint, minDistance);
        }

        return cache.get(startPoint);
    }

    private Integer calculatePath(boolean[][] matrix, Coordinate startPoint, Coordinate goal, List<Coordinate> path, Map<Coordinate, Integer> cache) {
        return Stream.of(minPath(matrix, startPoint.up(), goal, path, cache),
                minPath(matrix, startPoint.left(), goal, path, cache),
                minPath(matrix, startPoint.down(), goal, path, cache),
                minPath(matrix, startPoint.right(), goal, path, cache)
        )
                .filter(Objects::nonNull)
                .min(naturalOrder())
                .map(dist -> dist + 1)
                .orElse(null);
    }

    static class Coordinate {
        final int row;
        final int column;

        public Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }

        Coordinate up() {
            return new Coordinate(row + 1, column);
        }

        Coordinate left() {
            return new Coordinate(row, column - 1);
        }

        Coordinate down() {
            return new Coordinate(row - 1, column);
        }

        Coordinate right() {
            return new Coordinate(row, column + 1);
        }

        @Override
        public String toString() {
            return row + "," + column;
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