package de.cristiano.marathon.hr;

import java.util.stream.Stream;

public class RepeatedString {

    static long repeatedString(String s, long n) {
        final long quantityA = count(s, "a");

        if (quantityA == 0) {
            return 0L;
        }

        final long wordLength = Long.valueOf(s.length());
        final long wordCompleteRepetitions = n / wordLength;
        final Long wordPartSize = n % wordLength;
        long quantityAWordPart = 0L;

        if (wordPartSize > 0) {
            final String wordPart = s.substring(0, wordPartSize.intValue());
            quantityAWordPart = count(wordPart, "a");
        }

        return quantityA * wordCompleteRepetitions + quantityAWordPart;
    }

    private static long count(final String string, final String characterToCount) {
        return Stream.of(string.split(""))
                .mapToLong(character -> character.equals("a") ? 1L : 0L)
                .sum();
    }
}
