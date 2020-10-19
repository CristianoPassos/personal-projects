package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day103Test {

    private final Day103 day103 = new Day103();

    @Test
    void addsUpSubSet_shouldSucceed() {
        //Given
        Integer[] set = {12, 1, 61, 5, 9, 2};

        //When
        final Integer[] subSet = day103.addsUpSubSet(set, 24);

        //Then
        assertThat(subSet).containsExactlyInAnyOrder(12, 9, 2, 1);

    }
}
