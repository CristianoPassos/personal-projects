package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class K2 {
    private final static String ZERO = "0";

    @Test
    void baseCase_shouldSucceed() {
        //Given
        int[] A = {2, 3, 4, 1, 5};

        //When
        final String one = numberToOrdinal(1);
        final String two = numberToOrdinal(2);
        final String three = numberToOrdinal(3);
        final String eleven = numberToOrdinal(11);
        final String twelve = numberToOrdinal(12);
        final String thirdTeen = numberToOrdinal(13);
        final String hundredTeen = numberToOrdinal(213);
        final String seven = numberToOrdinal(7);
        final String eight = numberToOrdinal(8);
        final String nine = numberToOrdinal(9);

        //Then
        assertThat(one, is("1st"));
        assertThat(two, is("2nd"));
        assertThat(three, is("3rd"));
        assertThat(eleven, is("11th"));
        assertThat(twelve, is("12th"));
        assertThat(thirdTeen, is("13th"));
        assertThat(hundredTeen, is("213th"));
        assertThat(seven, is("7th"));
        assertThat(eight, is("8th"));
        assertThat(nine, is("9th"));
    }

    private final static List<Formatter> FORMATTERS = Arrays.asList(new TeensFormatter(), new OneFormatter(), new TwoFormatter(), new ThreeFormatter());

    private final static Formatter GLOBAL_FORMATTER = new GlobalFormatter();

    public static String numberToOrdinal(Integer number) {
        if (number == 0) {
            return ZERO;
        }

        return FORMATTERS.stream()
                .filter(formatter -> formatter.canFormat(number))
                .findFirst()
                .map(formatter -> formatter.format(number))
                .orElseGet(() -> GLOBAL_FORMATTER.format(number));
    }


    interface Formatter {
        boolean canFormat(Integer number);

        String getOrdinalIndicator();

        default String format(Integer number) {
            return number + getOrdinalIndicator();
        }
    }

    static class TeensFormatter implements Formatter {
        private final static String ORDINAL_INDICATOR = "th";

        @Override
        public boolean canFormat(Integer number) {
            int lastTwoDigits = number % 100;

            return isTeensCase(number) || isTeensCase(lastTwoDigits);
        }

        @Override
        public String getOrdinalIndicator() {
            return ORDINAL_INDICATOR;
        }

        private boolean isTeensCase(Integer number) {
            return number > 10 && number < 14;
        }

    }

    static class OneFormatter implements Formatter {
        private final static String ORDINAL_INDICATOR = "st";


        @Override
        public boolean canFormat(Integer number) {
            int firstDigit = number % 10;

            return isOneCase(number) || isOneCase(firstDigit);
        }

        private boolean isOneCase(Integer number) {
            return number == 1;
        }

        @Override
        public String getOrdinalIndicator() {
            return ORDINAL_INDICATOR;
        }
    }


    static class TwoFormatter implements Formatter {
        private final static String ORDINAL_INDICATOR = "nd";

        @Override
        public boolean canFormat(Integer number) {
            int firstDigit = number % 10;

            return isTwoCase(number) || isTwoCase(firstDigit);
        }

        private boolean isTwoCase(Integer number) {
            return number == 2;
        }

        @Override
        public String getOrdinalIndicator() {
            return ORDINAL_INDICATOR;
        }
    }


    static class ThreeFormatter implements Formatter {
        private final static String ORDINAL_INDICATOR = "rd";

        @Override
        public boolean canFormat(Integer number) {
            int firstDigit = number % 10;

            return isThreeCase(number) || isThreeCase(firstDigit);
        }

        private boolean isThreeCase(Integer number) {
            return number == 3;
        }

        @Override
        public String getOrdinalIndicator() {
            return ORDINAL_INDICATOR;
        }
    }

    static class GlobalFormatter implements Formatter {
        private final static String ORDINAL_INDICATOR = "th";

        @Override
        public boolean canFormat(Integer number) {
            return true;
        }

        @Override
        public String getOrdinalIndicator() {
            return ORDINAL_INDICATOR;
        }
    }

}
