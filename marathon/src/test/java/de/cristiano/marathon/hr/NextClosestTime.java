package de.cristiano.marathon.hr;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class NextClosestTime {

    @Test
    void nextClosestTime_shouldSucceed() {
        //Given
        String first = "19:34";
        String second = "11:11";

        //When
        final Set<Integer> numbers = new HashSet<>();

        numbers.add(first.charAt(0) - '0');
        numbers.add(first.charAt(1) - '0');
        numbers.add(first.charAt(3) - '0');
        numbers.add(first.charAt(4) - '0');


        final int firstMinDiff = nextClosestTime(numbers, toMinutes(first), "");
        String firstNextClosestTime = toHours(toMinutes(first) + firstMinDiff);

        numbers.clear();
        numbers.add(second.charAt(0) - '0');
        numbers.add(second.charAt(1) - '0');
        numbers.add(second.charAt(3) - '0');
        numbers.add(second.charAt(4) - '0');

        final int secondMinDiff = nextClosestTime(numbers, toMinutes(second), "");
        String secondNextClosestTime = toHours(toMinutes(second) + secondMinDiff);

        //Then
        assertThat(firstNextClosestTime, is("19:39"));
        assertThat(secondNextClosestTime, is("11:11"));
    }

    private String toHours(int nextClosestTime) {
        int adjustedTime = 0;

        if (nextClosestTime >= 1439) {
            adjustedTime = nextClosestTime - 1439;
        } else {
            adjustedTime = nextClosestTime;
        }

        int hours = adjustedTime / 60;

        return String.format("%s:%s",
                hours,
                adjustedTime - (hours * 60));
    }

    private int nextClosestTime(Set<Integer> numbers, int minutes, String timeHour) {
        final int position = timeHour.length();

        if (position == 5) {
            final int difference = toMinutes(timeHour) - minutes;

            if (difference <= 0) {
                return (2 * 600) + (3 * 60) + (5 * 10) + 9 - difference;
            }

            return difference;
        }

        int minDifference = Integer.MAX_VALUE;

        if (position == 0) {
            for (Integer number : numbers) {
                if (number <= 2) {
                    minDifference = Math.min(minDifference, nextClosestTime(numbers, minutes, number.toString()));
                }
            }
        }

        if (position == 1) {
            for (Integer number : numbers) {
                if (timeHour.charAt(0) == '2' && number <= 3) {
                    minDifference = Math.min(minDifference, nextClosestTime(numbers, minutes, timeHour + number.toString() + ":"));
                }

                if (timeHour.charAt(0) == '0' || timeHour.charAt(0) == '1') {
                    minDifference = Math.min(minDifference, nextClosestTime(numbers, minutes, timeHour + number.toString() + ":"));
                }

            }
        }

        if (position == 3) {
            for (Integer number : numbers) {
                if (number <= 5) {
                    minDifference = Math.min(minDifference, nextClosestTime(numbers, minutes, timeHour + number.toString()));
                }
            }
        }

        if (position == 4) {
            for (Integer number : numbers) {
                minDifference = Math.min(minDifference, nextClosestTime(numbers, minutes, timeHour + number.toString()));
            }
        }

        return minDifference;
    }

    private int toMinutes(String timeHour) {
        return Integer.parseInt(timeHour.substring(0, 2)) * 60
                + Integer.parseInt(timeHour.substring(3, 5));
    }
}
