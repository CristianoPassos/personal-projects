package de.cristiano.marathon.dcp;

/**
 * Given an array of numbers, find the maximum sum of any contiguous subarray of the array.
 */
public class Day102 {

    int maxContiguousSum(int[] array) {
        int maxSum = 0;
        int maxEndingHere = 0;

        for (int index = 0; index < array.length; index++) {
            maxEndingHere = maxEndingHere + array[index];

            if (maxEndingHere < 0) {
                maxEndingHere = 0;
            }

            maxSum = Math.max(maxSum, maxEndingHere);
        }

        return maxSum;
    }

}
