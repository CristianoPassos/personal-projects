package de.cristiano.marathon.daily;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.jupiter.api.Assertions.*;

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
        final Set<Integer> expectedValues = Set.of(1, 2, 3, 4, 5, 6, 7);

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