package de.cristiano.marathon.daily;

import java.util.Stack;

/**
 * Implement a queue using two stacks. Recall that a queue is a FIFO (first-in, first-out)
 * data structure with the following methods: enqueue,
 * which inserts an element into the queue, and dequeue, which removes it.
 */
public class Day53 {

    private final Stack<Integer> enqueue = new Stack<>();

    private final Stack<Integer> dequeue = new Stack<>();

    void enqueue(int element) {
        if (!dequeue.isEmpty()) {
            move(dequeue, enqueue);
        }

        enqueue.push(element);
    }

    private void move(Stack<Integer> to, Stack<Integer> from) {
        while (!to.isEmpty()) {
            from.add(to.pop());
        }
    }

    int dequeue() {
        if (!enqueue.isEmpty()) {
            move(enqueue, dequeue);
        }

        return dequeue.pop();
    }
}
