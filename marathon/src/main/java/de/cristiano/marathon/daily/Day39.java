package de.cristiano.marathon.daily;

/**
 * Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
 */
public class Day39 {

    int numWays(String message) {
        return numWays(message, 1, 0) + numWays(message, 2, 1);
    }

    private int numWays(String message, int indexSize, int index) {
        if (index == message.length()) {
            return 1;
        }

        final int currentNumber = indexSize == 2 ? numberFormedBy(message.charAt(index - 1), message.charAt(index)) :
                numberAt(message, index);

        if (currentNumber == 0) {
            return 0;
        }

        int numWays = 0;

        final int nextIndex = index + 1;
        final int secondNextIndex = index + 2;

        numWays += numWays(message, 1, nextIndex);

        if (secondNextIndex < message.length()) {

            final Character nextChar = message.charAt(nextIndex);
            final Character secondNextChar = message.charAt(secondNextIndex);
            final int nextNumber = numberFormedBy(nextChar, secondNextChar);

            if (toInt(nextChar) != 0 && nextNumber <= 26) {
                numWays += numWays(message, 2, secondNextIndex);
            }
        }

        return numWays;
    }

    private int toInt(Character nextChar) {
        return Integer.parseInt(nextChar.toString());
    }

    private int numberFormedBy(Character current, Character nextChar) {
        return Integer.parseInt(current.toString() + nextChar.toString());
    }

    private int numberAt(String message, int index) {
        return Integer.parseInt(Character.toString(message.charAt(index)));
    }
}
