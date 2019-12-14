package de.cristiano.marathon.daily;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.join;
import static java.util.Collections.nCopies;

/**
 * Write an algorithm to justify text. Given a sequence of words and an integer line length k,
 * return a list of strings which represents each line, fully justified.
 * <p>
 * More specifically, you should have as many words as possible in each line.
 * There should be at least one space between each word.
 * Pad extra spaces when necessary so that each line has exactly length k.
 * Spaces should be distributed as equally as possible, with the extra spaces, if any, distributed starting from the left.
 * <p>
 * If you can only fit one word on a line, then you should pad the right-hand side with spaces.
 * <p>
 * Each word is guaranteed not to be longer than k.
 */
public class Day28Iterative {

    List<String> justifyText(String[] words, int length) {
        final List<String> lines = new ArrayList<>();
        final List<String> line = new ArrayList<>();
        int currentLineSize = 0;

        for (String word : words) {
            if (word.length() >= length && line.isEmpty()) {
                lines.add(word);
            } else if (word.length() >= length) {
                lines.add(justify(line, currentLineSize, length));
                line.clear();

                lines.add(word);
                currentLineSize = 0;
            } else if (currentLineSize + word.length() + line.size() < length) {
                line.add(word);
                currentLineSize += word.length();
            } else {
                lines.add(justify(line, currentLineSize, length));
                line.clear();

                line.add(word);
                currentLineSize = word.length();
            }
        }

        if (!line.isEmpty()) {
            lines.add(justify(line, currentLineSize, length));
        }

        return lines;
    }

    private String justify(List<String> line, int wordsLength, int lineLength) {
        final StringBuilder builder = new StringBuilder();

        if (line.size() == 1) {
            final String word = line.get(0);
            builder.append(word);

            int padding = lineLength - word.length();

            if (padding > 0) {
                builder.append(repeatString(" ", padding));
            }

            return builder.toString();
        }

        final int wordsToPad = line.size() - 1;
        final int padding = lineLength - wordsLength;
        final int paddingPerWord = padding / wordsToPad;
        final int extraPaddingUntil = padding % wordsToPad;

        for (int index = 0; index < line.size() - 1; index++) {
            int wordPadding = index + 1 <= extraPaddingUntil ? paddingPerWord + extraPaddingUntil : paddingPerWord;

            builder.append(line.get(index))
                    .append(repeatString(" ", wordPadding));
        }

        builder.append(line.get(line.size() - 1));

        return builder.toString();
    }

    private String repeatString(String string, int times) {
        return join("", nCopies(times, string));
    }
}
