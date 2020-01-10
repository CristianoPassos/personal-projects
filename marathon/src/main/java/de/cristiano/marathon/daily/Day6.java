package de.cristiano.marathon.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
class Day6 {

    int[] twoSum(int[] array, int target) {
        final Map<Integer, Integer> lookupTable = new HashMap<>();

        for (int index = 0; index < array.length; index++) {
            final int complement = target - array[index];

            if (lookupTable.containsKey(complement)) {
                return new int[]{lookupTable.get(complement), index};
            } else {
                lookupTable.put(array[index], index);
            }
        }

        throw new IllegalArgumentException("Could not locate elements");
    }

}
