package de.cristiano.marathon.daily;

import java.util.Set;

/**
 * Given a dictionary of words and a string made up of those words (no spaces), return the original sentence in a list.
 * If there is more than one possible reconstruction, return any of them.
 * If there is no possible reconstruction, then return null.
 */
public class Day101 {

    String originalSentence(Set<String> dictionary, String sentence) {
        final StringBuilder completeSentence = new StringBuilder();

        int currentIndex = 0;

        while (currentIndex < sentence.length()) {
            final int offSet = currentIndex;

            final String nextWord = dictionary.stream()
                    .filter(word -> sentence.startsWith(word, offSet))
                    .findFirst()
                    .orElse(null);

            if (nextWord == null) {
                return null;
            }

            completeSentence.append(nextWord);
            completeSentence.append(" ");
            currentIndex += nextWord.length();
        }

        return completeSentence.toString().trim();
    }
}
