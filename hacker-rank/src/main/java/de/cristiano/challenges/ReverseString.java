package de.cristiano.challenges;

import java.util.Arrays;

/**
 * Creates a Function that reverses a String.
 * <p>
 * For example, when called with "Hi My Name is:" it should return:
 * ":si eman yM iH"
 */
class ReverseString {

    String solution(String string) {
        if (string == null || string.isBlank()) {
            return "";
        }

        final char[] characters = string.toCharArray();
        final StringBuilder reversedString = new StringBuilder();
        int lastCharacterIndex = characters.length - 1;

        for (int index = 0; index <= lastCharacterIndex; index++) {
            reversedString.append(characters[lastCharacterIndex - index]);
        }

        return reversedString.toString();
    }
}


