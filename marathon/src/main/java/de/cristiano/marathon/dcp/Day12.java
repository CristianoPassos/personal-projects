package de.cristiano.marathon.dcp;

import java.util.EmptyStackException;

import static java.util.Objects.isNull;

/**
 * Implement a stack that has the following methods:
 * <p/>
 * push(val), which pushes an element onto the stack
 * <p/>
 * pop(), which pops off and returns the topmost element of the stack. If there are no elements in the stack,
 * then it should throw an error or return null.
 * <p/>
 * max(), which returns the maximum value in the stack currently. If there are no elements in the stack,
 * then it should throw an error or return null.
 * <p/>
 * <b>Each method should run in constant time.</b>
 */
public class Day12 {

    private final int[] stack;

    private int currentPosition = -1;

    private Integer max;

    public Day12(int size) {
        stack = new int[size];
    }

    void push(int val) {
        currentPosition++;

        if (isNull(max)) {
            max = val;
            stack[currentPosition] = val;
            return;
        }

        if (val > max) {
            stack[currentPosition] = (2 * val - max);
            max = val;
        } else {
            stack[currentPosition] = val;
        }
    }

    int pop() {
        if (currentPosition == -1) {
            throw new EmptyStackException();
        }
        int element = stack[currentPosition];
        currentPosition--;

        if (element > max) {
            int maxTemp = max;
            max = 2 * max - element;
            return maxTemp;
        }

        return element;
    }

    int max() {
        if (isNull(max) || currentPosition == -1) {
            throw new EmptyStackException();
        }

        return max;
    }
}
