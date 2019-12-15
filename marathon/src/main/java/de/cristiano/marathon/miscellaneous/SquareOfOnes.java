package de.cristiano.marathon.miscellaneous;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Given a Matrix find the largest square made of 1's
 */
public class SquareOfOnes {

    int maxSquare(int[][] matrix) {

        Map<Integer,Integer> frequency = new HashMap<>();

        int maxSquareSize = 0;

        int[][] squareSizes = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (row == 0 || column == 0) {
                    squareSizes[row][column] = matrix[row][column];
                } else if (matrix[row][column] == 0) {
                    squareSizes[row][column] = 0;
                } else {
                    squareSizes[row][column] = calculateSquareSize(squareSizes, row, column);
                }
                maxSquareSize = Math.max(maxSquareSize, squareSizes[row][column]);
            }
        }

        return maxSquareSize;
    }

    private int calculateSquareSize(int[][] computedSquareSizes, int row, int column) {
        if (row < 0 || row >= computedSquareSizes.length) {
            return 0;
        }

        if (column < 0 || column >= computedSquareSizes[row].length) {
            return 0;
        }

        return IntStream.of(computedSquareSizes[row - 1][column],
                computedSquareSizes[row][column - 1],
                computedSquareSizes[row - 1][column - 1])
                .min()
                .getAsInt() + 1;
    }
}
