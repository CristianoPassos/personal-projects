package de.cristiano.marathon.dcp;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.joining;

/**
 * Given a string, find the palindrome that can be made by inserting the fewest number of characters as possible anywhere in the word.
 * If there is more than one palindrome of minimum length that can be made,
 * return the lexicographically earliest one (the first one alphabetically).
 */
public class Day34Iterative {

    String minPalindrome(String string) {
        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int index = 0; index < string.length(); index++) {
            charFrequency.compute(string.charAt(index), (k, v) -> v == null ? 1 : ++v);
        }

        final Set<Character> charsToAdd = charFrequency.entrySet().stream()
                .filter(e -> e.getValue() % 2 == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if ((charsToAdd.size() + string.length()) % 2 == 1 || charsToAdd.size() == string.length()) {

            final Character middle = charsToAdd.stream()
                    .max(naturalOrder())
                    .orElseThrow(RuntimeException::new);

            charsToAdd.addAll(charFrequency.keySet());
            charsToAdd.remove(middle);

            return build(charsToAdd, naturalOrder()) + middle + build(charsToAdd, reverseOrder());
        }

        charsToAdd.addAll(charFrequency.keySet());

        return build(charsToAdd, naturalOrder()) + build(charsToAdd, reverseOrder());
    }

    private String build(Set<Character> charsToAdd, Comparator<Character> comparator) {
        return charsToAdd.stream()
                .sorted(comparator)
                .map(c -> Character.toString(c))
                .collect(joining(""));
    }
}
