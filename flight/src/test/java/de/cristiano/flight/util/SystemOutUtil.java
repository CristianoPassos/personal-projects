package de.cristiano.flight.util;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;

@UtilityClass
public class SystemOutUtil {

    private final PrintStream DEFAULT_PRINT_STREAM = System.out;

    private ByteArrayOutputStream output;

    public void captureSystemOutput() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output, true));
    }

    public String formatAsNewLines(@Nonnull final Stream<Integer> stream) {
        return stream
            .map(value -> value + lineSeparator())
            .reduce("", String::concat);
    }

    public void restoreSystemOutput() {
        System.setOut(DEFAULT_PRINT_STREAM);
    }

    public String retrieveSystemOutput() {
        return output == null ? "" : output.toString();
    }
}
