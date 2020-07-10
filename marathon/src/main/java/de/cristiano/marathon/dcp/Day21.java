package de.cristiano.marathon.dcp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers and a number k, where 1 <= k <= length of the array,
 * compute the maximum values of each subarray of length k.
 */
public class Day21 {


    Integer[] maximumValuesOfSubArrays(int[] array, int subArraySize) {
        Integer[] maxValues = new Integer[array.length - subArraySize + 1];

        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(0);

        for (int index = 0; index < array.length; index++) {
            final int element = array[index];
            final int currentSubArrayStartIndex = index - (subArraySize - 1);

            if (deque.peek() < currentSubArrayStartIndex) {
                deque.pop();
            }

            if (element >= array[deque.peek()]) {
                deque.clear();
                deque.push(index);
            } else if (element >= deque.peekLast() || deque.size() == 1) {
                deque.add(index);
            }

            if (index >= subArraySize - 1) {
                maxValues[currentSubArrayStartIndex] = array[deque.peek()];
            }
        }

        return maxValues;
    }
}
