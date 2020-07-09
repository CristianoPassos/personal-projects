package de.cristiano.marathon.miscellaneous;

import static java.lang.String.format;

/**
 * Hourglass array representation
 *
 *[0][0,1,2]
 *   [1][1]
 *[2][0, 1, 2]
 */
public class Array2d {

    private static int hourglass(int[][] arr, int x, int y) {
        return arr[y][x] + arr[y][x + 1] + arr[y][x + 2]
                + arr[1 + y][1 + x]
                + arr[y + 2][x] + arr[y + 2][x + 1] + arr[y + 2][x + 2];
    }

    static int hourglassSum(int[][] arr) {
        int maxHourglass = Integer.MIN_VALUE;

        for (int positionX = 0; positionX < 4; positionX++) {
            for (int positionY = 0; positionY < 4; positionY++) {

                final int hourglass = hourglass(arr, positionX, positionY);

                if (hourglass > maxHourglass) {
                    maxHourglass = hourglass;
                }
            }

        }

        return maxHourglass;
    }
}
