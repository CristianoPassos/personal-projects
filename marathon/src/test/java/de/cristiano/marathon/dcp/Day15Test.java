package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day15Test {

    private Day15 day15 = new Day15();

    @Test
    void calculateMedian_shouldSucceed() {
        //Given
        int[] array = {2, 1, 5, 7, 2, 0, 5};

        //When
        final float[] medians = day15.medians(array);

        //Then
        final Float[] expected = {2F, 1.5F, 2F, 3.5F, 2F, 2F, 2F};

        assertThat(toObject(medians), arrayContaining(expected));
    }
}
