package de.cristiano.marathon.dcp;

/**
 * A builder is looking to build a row of N houses that can be of K different colors.
 * He has a goal of minimizing cost while ensuring that no two neighboring houses are of the same color.
 * <p>
 * Given an N by K matrix where the nth row and kth column represents the cost to build the nth house with kth color,
 * return the minimum cost which achieves this goal.
 */
public class Day19 {

    int minCost(int[][] paintCost) {
        int minCost = Integer.MAX_VALUE;
        final int[][] cache = new int[paintCost.length][paintCost[0].length];

        for (int paint = 0; paint < paintCost.length; paint++) {
            minCost = Math.min(minCost, totalCost(paintCost, 0, paint, cache));
        }

        return minCost;
    }

    private int totalCost(int[][] paintCosts, int house, int paint, int[][] cache) {
        if (house == paintCosts[0].length - 1) {
            return paintCosts[paint][house];
        }

        final int cheapestNextPaintIndex = minPaintCost(paintCosts, house + 1, paint);

        if (cache[cheapestNextPaintIndex][house + 1] == 0) {
            cache[cheapestNextPaintIndex][house + 1] = totalCost(paintCosts, house + 1, cheapestNextPaintIndex, cache);
        }

        return paintCosts[paint][house] + cache[cheapestNextPaintIndex][house + 1];
    }

    private int minPaintCost(int[][] paintCost, int house, int neighborPaint) {
        int minCost = Integer.MAX_VALUE;
        int index = -1;

        for (int paint = 0; paint < paintCost.length; paint++) {
            if (paint != neighborPaint && paintCost[paint][house] < minCost) {
                minCost = paintCost[paint][house];
                index = paint;
            }
        }

        return index;
    }


}
