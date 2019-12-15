package de.cristiano.marathon.daily;

import java.util.Arrays;

/**
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
 * <p>
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 * <p>
 * Follow-up: what if you can't use division?
 */
class Day2 {

    /**
     * Time Complexity O(n)
     * Space Complexity O(n)
     */
    int[] solution(int[] numbers) {
        int product = 1;
        int zeroCount = 0;
        int zeroPosition = -1;
        int[] result = new int[numbers.length];

        for (int index = 0; index < numbers.length; index++) {
            if (numbers[index] == 0) {
                zeroCount++;
                if (zeroCount >= 2) {
                    break;
                }
                zeroPosition = index;
            } else {
                product *= numbers[index];
            }
        }

        if (zeroCount == 0) {
            for (int index = 0; index < numbers.length; index++) {
                result[index] = product / numbers[index];
            }
        }

        if (zeroCount == 1) {
            Arrays.fill(result, 0);
            result[zeroPosition] = product;
        }

        if (zeroCount >= 2) {
            Arrays.fill(result, 0);
        }

        return result;
    }
}
