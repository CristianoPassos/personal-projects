package de.cristiano.marathon.daily;

import java.util.HashSet;

/**
 * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers.
 * Numbers can be 0 or negative.
 */
public class Day25 {

    int largesAdjacentSum(int[] numbers) {
        int maxSum = 0;

        for (int index = 0; index < numbers.length; index++) {
            maxSum = Math.max(maxSum, adjacentSum(numbers, index));
        }

        return maxSum;
    }

    private int adjacentSum(int[] numbers, int index) {
        int sum = 0;
        final HashSet<Integer> adjacent = new HashSet<>();

        while (index < numbers.length) {
            final int number = numbers[index];

            if (!adjacent.contains(number)) {
                adjacent.clear();
                adjacent.add(safeGet(numbers, index - 1));
                adjacent.add(safeGet(numbers, index + 1));
                sum += number;
            }

            index++;
        }

        return sum;
    }

    private Integer safeGet(int[] numbers, int index) {
        if (index < 0 || index >= numbers.length) {
            return null;
        }

        return numbers[index];
    }
}
