package de.cristiano.marathon.daily;

/**
 * You are given an array of non-negative integers that represents a two-dimensional elevation map
 * where each element is unit-width wall and the integer is the height.
 * Suppose it will rain and all spots between two walls get filled up.
 * <p>
 * Compute how many units of water remain trapped on the map in O(N) time and O(1) space.
 */
public class Day30 {

    int trappedWater(int[] elevationMap) {
        final int totalWalls = elevationMap.length;

        if (totalWalls == 0 || totalWalls == 1) {
            return 0;
        }
        if (totalWalls == 2) {
            return Math.abs(elevationMap[0] - elevationMap[1]);
        }

        int leftWallHeight = elevationMap[0];
        int rightWallHeight = leftWallHeight;
        int maxHeightInSerie = 0;
        int elementsInSerie = 0;
        int currentReservoir = 0;
        int totalReservoir = 0;

        for (int index = 1; index < totalWalls - 1; index++) {
            final int wallHeight = elevationMap[index];

            if (wallHeight == rightWallHeight) {
                totalReservoir += currentReservoir;

                currentReservoir = 0;
                elementsInSerie = 0;
            } else if (wallHeight > rightWallHeight) {
                totalReservoir += currentReservoir;

                currentReservoir = 0;
                elementsInSerie = 0;
                rightWallHeight = wallHeight;
                leftWallHeight = rightWallHeight;
            } else {
                maxHeightInSerie = Math.max(maxHeightInSerie, wallHeight);
                elementsInSerie++;
                currentReservoir += leftWallHeight - wallHeight;
            }
        }

        final int damWallsDiff = leftWallHeight - elevationMap[totalWalls - 1];

        if (damWallsDiff > 0) {
            totalReservoir += currentReservoir - (damWallsDiff * elementsInSerie);
        }

        return totalReservoir;
    }
}
