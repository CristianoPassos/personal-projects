package de.cristiano.marathon.daily;

/**
 * You run an e-commerce website and want to record the last N order ids in a log. Implement a data structure to accomplish this,
 * with the following API:
 * <p>
 * - record(order_id): adds the order_id to the log
 * - get_last(i): gets the ith last element from the log. i is guaranteed to be smaller than or equal to N.
 */
public class Day10 {

    private final int[] log;
    private int circularIndex = 0;

    Day10(int logSize) {
        log = new int[logSize];
    }

    void record(int order_id) {
        log[circularIndex] = order_id;
        incrementIndex();
    }

    private void incrementIndex() {
        circularIndex = (circularIndex + 1) % log.length;
    }

    int get_last(int i) {
        return log[circularIndex - i + log.length] % log.length;
    }
}
