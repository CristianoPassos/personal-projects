package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;

class Day103Test {

    private final Day103 day103 = new Day103();

    @Test
    void addsUpSubSet_shouldSucceed() {
        //Given
        Integer[] set = {12, 1, 61, 5, 9, 2};

        //When
        final Integer[] subSet = day103.addsUpSubSet(set, 24);

        //Then
        assertThat(subSet, arrayContainingInAnyOrder(12, 9, 2, 1));

    }
}