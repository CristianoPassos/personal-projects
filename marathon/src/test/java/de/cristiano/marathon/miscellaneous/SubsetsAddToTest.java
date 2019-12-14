package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SubsetsAddToTest {

    private final SubsetsAddTo addsTo = new SubsetsAddTo();

    @Test
    void subSets_shouldSucceed() {
        //Given
        int[] set = {2, 4, 6, 10};

        //When
        final int subSets = addsTo.subSets(set, 16);

        //Then
        assertThat(subSets, is(2));
    }
}