package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

/**
 * Given a mapping of digits to letters (as in a phone number), and a digit string,
 * return all possible letters the number could represent. You can assume each valid number in the mapping is a single digit.
 * <p>
 * For example if {“2”: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …} then “23”
 * should return [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
 */
public class Day81 {

    private final static char[][] KEYBOARD = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    @Test
    void baseCase_shouldSucceed() {
        //When
        final Set<String> combinations12 = doCombination(12);
        final Set<String> combinations23 = doCombination(23);

        //Then
        assertThat(combinations12, containsInAnyOrder("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
        assertThat(combinations23, containsInAnyOrder("dg", "dh", "di", "eg", "eh", "ei", "fg", "fh", "fi"));
    }

    private Set<String> doCombination(int number) {
        final char[] digits = Integer.toString(number).toCharArray();
        final char[][] keyboardNumbers = new char[digits.length][];
        final Set<String> combinations = new HashSet<>();

        for (int index = 0; index < digits.length; index++) {
            keyboardNumbers[index] = KEYBOARD[digits[index] - '1'];
        }

        doCombination(keyboardNumbers, combinations, 0, "");

        return combinations;
    }

    private void doCombination(char[][] keyboard, Set<String> combinations, int row, String currentCombination) {
        if (row >= keyboard.length) {
            combinations.add(currentCombination);
            return;
        }

        for (char c : keyboard[row]) {
            doCombination(keyboard, combinations, row + 1, currentCombination + c);
        }
    }
}
