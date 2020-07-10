package de.cristiano.marathon.dcp;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and an integer k, break up the string into multiple lines such that each line has a length of k or less.
 * You must break it up so that words don't break across lines. Each line has to have the maximum possible amount of words.
 * If there's no way to break the text up, then return null.
 */
public class Day104 {

    String[] breakUp(String string, int maxSize) {
        final String[] words = string.split(" ");

        final List<String> phrases = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            if (word.length() > maxSize) {
                return null;
            }

            if (word.length() <= maxSize - builder.length()) {
                builder.append(word + " ");
            } else {
                phrases.add(builder.toString().trim());
                builder = new StringBuilder();
                builder.append(word + " ");
            }
        }

        phrases.add(builder.toString().trim());

        return phrases.toArray(new String[]{});
    }


    String[] breakUp2(String string, int maxSize) {

        int start = 0;

        List<String> phrases = new ArrayList<>();

        do {
            final Integer minLength = minLength(string, start, maxSize);

            if (minLength == null) {
                return null;
            }

            phrases.add(string.substring(start, minLength));

            start = minLength + 1;
        } while (start < string.length());

        return phrases.toArray(new String[]{});

    }

    private Integer minLength(String string, int start, int maxSize) {
        int index = start + maxSize;

        if (index >= string.length()) {
            return string.length();
        }

        for (; index >= start; index--) {
            if (string.charAt(index) == ' ') {
                return index;
            }
        }

        return null;
    }
}
