package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.fail;

class Day11Test {

    private final Day11 day11 = new Day11();

    @Test
    void rand7() {
        //Given
        int trails = 1000;
        int[] numbers = new int[trails];

        //When
        for (int i = 0; i < trails; i++) {
            numbers[i] = day11.rand7();
        }

        //Then
        final Set<Integer> values = new HashSet<>();
        final Set<Integer> expectedValues = new HashSet<>();

        expectedValues.add(1);
        expectedValues.add(2);
        expectedValues.add(3);
        expectedValues.add(4);
        expectedValues.add(5);
        expectedValues.add(6);
        expectedValues.add(7);

        for (int i = 0; i < trails; i++) {
            int number = numbers[i];

            if (number < 0 || number > 7) {
                fail(String.format("Invalid number: [%s]", number));
            }

            values.add(number);
        }

        assertThat(values, containsInAnyOrder(expectedValues.toArray()));
    }
}