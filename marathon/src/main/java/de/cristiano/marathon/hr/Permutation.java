package de.cristiano.marathon.hr;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
    private final static char[][] KEYBOARD = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        final int[] numbers = new int[digits.length()];
        final List<String> permutation = new ArrayList<>();

        for (int index = 0; index < digits.length(); index++) {
            numbers[index] = digits.charAt(index) - '0';
        }

        doPermutation(numbers, 0, digits.length(), "", permutation);

        return permutation;
    }

    private void doPermutation(int[] numbers, int numberIndex, int wordSize, String currentWord, List<String> permutation) {

        if (currentWord.length() == wordSize) {
            permutation.add(currentWord);
            return;
        }

        for (Character letter : KEYBOARD[numbers[numberIndex]]) {
            doPermutation(numbers, numberIndex + 1, wordSize, currentWord + letter, permutation);
        }
    }
}
