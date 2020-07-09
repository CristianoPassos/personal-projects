package de.cristiano.marathon.daily;

import java.util.Random;

/**
 * Given a function that generates perfectly random numbers between 1 and k (inclusive),
 * where k is an input, write a function that shuffles a deck of cards represented as an array using only swaps.
 */
public class Day29 {

    private final Random random = new Random();

    String[] shuffle(String[] array, int k) {
        String[] shuffled = array.clone();

        for (int index = 0; index < shuffled.length; index++) {
            final String temp = shuffled[index];
            final int indexToShuffle = random(k, shuffled.length);

            shuffled[index] = shuffled[indexToShuffle];
            shuffled[indexToShuffle] = temp;
        }

        return shuffled;
    }

    private int random(float max, float length) {
        final int times = ((int) Math.ceil(length / max));

        final int randomValue = calculateRandomValue((int) max, (int) length, times);

        return randomValue;
    }

    private int calculateRandomValue(int limit, int maxValue, int times) {
        int randomValue = 0;

        for (int index = 0; index <= times; index++) {
            randomValue += random.nextInt(limit + 1);
        }

        if (randomValue > maxValue - 1) {
            return calculateRandomValue(limit, maxValue, times);
        }

        return randomValue;
    }
}