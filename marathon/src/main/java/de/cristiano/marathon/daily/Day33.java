package de.cristiano.marathon.daily;

/**
 * Given an undirected graph represented as an adjacency matrix and an integer k,
 * write a function to determine whether each vertex in the graph can be colored such that no two adjacent
 * vertices share the same color using at most k colors.
 */
public class Day33 {


    boolean canBeColored(int[][] adjacencyMatrix, int colors) {

        for (int row = 0; row < adjacencyMatrix.length; row++) {
            int degree = 0;
            for (int column = 0; column < adjacencyMatrix[row].length; column++) {
                if (adjacencyMatrix[row][column] == 1) {
                    degree++;
                }
            }

            if (degree > colors) {
                return false;
            }
        }

        return true;
    }
}
