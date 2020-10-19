package de.cristiano.marathon.dcp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N,
 * write a function that returns the number of unique ways you can climb the staircase.
 * The order of the steps matters.
 */
public class Day20 {

    int climbWays(int steps, int... stepLengths) {
        Map<Integer, Integer> cache = new HashMap<>();

        return climbWaysMemoization(steps, stepLengths, cache);
    }

    private int climbWaysMemoization(int steps, int[] stepLengths, Map<Integer, Integer> cache) {
        if (steps == 0) {
            return 1;
        }

        int min = Arrays.stream(stepLengths)
                .min()
                .orElse(Integer.MAX_VALUE);

        if (steps < min) {
            return 0;
        }

        int ways = 0;

        for (int stepLength : stepLengths) {
            if (steps >= stepLength) {
                final int nextWay = steps - stepLength;

                if (!cache.containsKey(nextWay)) {
                    cache.put(nextWay, climbWays(nextWay, stepLengths));
                }

                ways += cache.get(nextWay);
            }
        }

        return ways;
    }
}
