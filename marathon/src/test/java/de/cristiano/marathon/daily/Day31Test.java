package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class Day31Test {

    private final Day31 day31 = new Day31();

    @Test
    void editDistance_shouldSucceed() {
        //When
        final List<Day31.Operation> operations = day31.editDistance("kitten", "sitting");

        //Then
        List<Day31.Operation> expected = new ArrayList<>();
        expected.add(new Day31.Operation(Day31.Operation.Value.SUBSTITUTE, 's', 0));
        expected.add(new Day31.Operation(Day31.Operation.Value.SUBSTITUTE, 'i', 4));
        expected.add(new Day31.Operation(Day31.Operation.Value.INSERT, 'g', 6));

        assertThat(operations, containsInAnyOrder(expected.toArray()));
    }
}