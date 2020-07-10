package de.cristiano.marathon.dcp;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * <p>
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * <p>
 * Bonus: Can you do this in one pass?
 */
class Day1 {

    /**
     * Time Complexity O(n)
     * Space Complexity O(n)
     */
    boolean solution(int[] numbers, int k) {
        if (numbers.length < 2) {
            return false;
        }

        Set<Integer> evaluated = new HashSet<>();

        for (int number : numbers) {
            int complement = k - number;

            if (evaluated.contains(complement)) {
                return true;
            }

            evaluated.add(number);
        }

        return false;
    }
}
