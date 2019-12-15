package de.cristiano.marathon.daily;


import java.util.InputMismatchException;

/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent
 * repeated successive characters as a single count and character.
 * For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 * <p>
 * Implement run-length encoding and decoding.
 * You can assume the string to be encoded have no digits and consists solely of alphabetic characters.
 * You can assume the string to be decoded is valid.
 */
public class Day17 {

    String encode(String string) {
        if (string.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        char currentLetter = string.charAt(0);
        int letterCount = 1;
        sb.append(currentLetter);

        for (int index = 1; index < string.length(); index++) {
            final char nextLetter = string.charAt(index);

            if (nextLetter == currentLetter) {
                letterCount++;
            } else {
                sb.append(letterCount);
                sb.append(nextLetter);
                letterCount = 1;
                currentLetter = nextLetter;
            }
        }

        sb.append(letterCount);

        return sb.toString();
    }

    String decode(String encoded) {
        if (encoded.isEmpty()) {
            return "";
        }

        if (isInvalid(encoded)) {
            throw new InputMismatchException(String.format("%s is invalid"));
        }

        StringBuilder sb = new StringBuilder();

        for (int index = 0; index < encoded.length(); index++) {
            final char element = encoded.charAt(index);

            if (Character.isDigit(element)) {
                final int occurrence = Character.getNumericValue(element);
                final char recurringChar = encoded.charAt(index - 1);

                for (int repeat = 0; repeat < occurrence; repeat++) {
                    sb.append(recurringChar);
                }

            } else {
                sb.append(element);
            }
        }

        return sb.toString();
    }

    private boolean isInvalid(String encoded) {
        return false;
    }
}
