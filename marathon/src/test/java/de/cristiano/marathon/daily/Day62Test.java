package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day62Test {

    private final Day62 day62 = new Day62();

    @Test
    void pathsTowardsGoal_shouldSucceed() {
        //When
        final int ways = day62.pathsTowardsGoal(5, 5);

        //Then
        assertThat(ways, is(70));
    }
}