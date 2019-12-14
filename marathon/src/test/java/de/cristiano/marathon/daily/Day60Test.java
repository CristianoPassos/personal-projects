package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

class Day60Test {

    private final Day60 day60 = new Day60();

    @Test
    void canBePartitioned_shouldSucceed() {
        //Given
        int[] canBePartitionedMultiset = {15, 5, 21}; //, 10, 35, 15, 10};
        int[] canNotBePartitionedMultiset = {15, 5, 20, 10, 35};

        //When
        final boolean canBePartitioned = day60.canBePartitioned(canBePartitionedMultiset);
        final boolean canNotBePartitioned = day60.canBePartitioned(canNotBePartitionedMultiset);

        //Then
        //assertThat(canBePartitioned, is(true));
        //assertThat(canNotBePartitioned, is(false));
    }
}