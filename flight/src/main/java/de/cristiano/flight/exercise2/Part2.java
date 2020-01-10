package de.cristiano.flight.exercise2;

public class Part2 {

    void countUpAndDown(int start, int end) {
        if (end < 0) {
            return;
        }

        if (start < end) {
            System.out.println(start);
            countUpAndDown(start + 1, end);
        } else {
            System.out.println(end);
            countUpAndDown(start, end - 1);
        }
    }
}
