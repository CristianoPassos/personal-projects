package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import static de.cristiano.marathon.daily.utils.Constants.EMPTY;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Using a read7() method that returns 7 characters from a file, implement readN(n) which reads n characters.
 */
public class Day82 {

    private final Queue<String> file = new ArrayDeque<>();

    @Test
    public void baseCase_shouldSucceed() {
        //Given
        file.clear();
        file.add("Hello w");
        file.add("orld");
        file.add("");

        //When
        final String read = readN(5);

        //Then
        assertThat(read, is("Hello"));
    }

    String readN(int n) {
        if (n < 0) {
            return EMPTY;
        }
        int completeCycles = n / 7;
        final int remainingChars = n % 7;

        final StringBuilder builder = new StringBuilder();

        while (completeCycles > 0) {
            builder.append(read7());
            completeCycles--;
        }

        if (remainingChars > 0) {
            final String lastLine = read7();

            if (lastLine.length() > remainingChars)
                builder.append(lastLine, 0, remainingChars);
            else
                builder.append(lastLine);
        }

        return builder.toString();
    }

    private String read7() {
        return file.poll();
    }
}
