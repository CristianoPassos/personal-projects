package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class Day13Test {

    private Day13 day13 = new Day13();

    @Test
    void powerSet_shouldSucceed() {
        //Given
        Set<Set<Integer>> powerSet = day13.powerSet(new HashSet<>(Arrays.asList(1,2,3)));

        //Then
        Set<Set<Integer>> expected = new HashSet<>();


        expected.add(new HashSet<>());
        expected.add(new HashSet<>(singletonList(1)));
        expected.add(new HashSet<>(singletonList(2)));
        expected.add(new HashSet<>(Arrays.asList(1, 2)));
        expected.add(new HashSet<>(singletonList(3)));
        expected.add(new HashSet<>(Arrays.asList(1, 3)));
        expected.add(new HashSet<>(Arrays.asList(2, 3)));
        expected.add(new HashSet<>(Arrays.asList(1, 2, 3)));

        assertThat(powerSet, containsInAnyOrder(expected.toArray()));
    }
}