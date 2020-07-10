package de.cristiano.marathon.dcp;

/**
 * A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e., maze[0][0]
 * and destination block is lower rightmost block i.e., maze[N-1][N-1].
 * A rat starts from source and has to reach the destination.
 * The rat can move only in two directions: forward and down.
 */
public class Day105 {

    int[][] solveMaze(int[][] maze, int startRow, int startColumn, int endRow, int endColumn) {
        int path[][] = new int[maze.length][maze[0].length];


        path[startRow][startColumn] = 1;


        solveMaze(maze, path, startRow, startColumn, endRow, endColumn);


        return path;
    }

    private boolean solveMaze(int[][] maze, int[][] path, int row, int column, int endRow, int endColumn) {

        path[row][column] = 1;

        if (row == endRow && column == endColumn) {
            return true;
        }

        if (maze[row][column] == 0) {
            path[row][column] = 0;
            return false;
        }

        if (!solveMaze(maze, path, row, column + 1, endRow, endColumn)) {
            if (!solveMaze(maze, path, row + 1, column, endRow, endColumn)) {
                path[row][column] = 0;

                return false;
            }
        }

        return true;
    }

}
