package de.cristiano.marathon.dcp;

import java.util.Random;

/**
 * Using a function rand5() that returns an integer from 1 to 5 (inclusive) with uniform probability,
 * implement a function rand7() that returns an integer from 1 to 7 (inclusive).
 */
public class Day11 {

    private final Random random = new Random();

    int rand7() {
        int i;

        do {
            i = 5 * (rand5() - 1) + rand5();
        } while (i > 21);

        return i % 7 + 1;
    }

    int rand5() {
        return random.nextInt(5) + 1;
    }
}
