package de.cristiano.flight.exercise2;

public class Part1 {

    void countUp(int start, int end) {
        System.out.println(start);

        if (start == end) {
            return;
        }

        countUp(start + 1, end);
    }

}
