package de.cristiano.marathon.daily;

import java.util.*;

/**
 * Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
 * <p>
 * For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 */
public class Day18 {

    String longestSubstringWithDistinctCharacters(String string, int distinctCharacters) {
        if (string.isEmpty() || distinctCharacters < 1) {
            return "";
        }
        if (string.length() == 1) {
            return string;
        }

        StringBuilder longest = null;

        List<StringBuilder> substrings = new ArrayList<>();

        for (int index = 0; index < string.length(); index++) {
            final char c = string.charAt(index);

            final Iterator<StringBuilder> iterator = substrings.iterator();

            while (iterator.hasNext()) {
                final StringBuilder next = iterator.next();

                if (uniqueCharactersLessOrEqualTo(next + Character.toString(c), distinctCharacters)) {
                    next.append(c);
                    if (longest == null || longest.length() < next.length()) {
                        longest = next;
                    }
                } else {
                    iterator.remove();
                }
            }


            final StringBuilder substring = new StringBuilder();
            substring.append(c);
            substrings.add(substring);
        }

        return longest.toString();
    }

    private boolean uniqueCharactersLessOrEqualTo(String string, int distinctCharacters) {
        final Set<Character> counter = new HashSet<>();

        for (char achar : string.toCharArray()) {
            counter.add(achar);
        }

        return counter
                .size() <= distinctCharacters;
    }
}
