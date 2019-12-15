package de.cristiano.marathon.daily;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day21Test {

    private final Day21 day21 = new Day21();

    public void maximumValues_shouldSucceed() {
        //Given
        int[] array = {8, 6, 4, 3, 1, 8, 5, 7};

        //When
        final Integer[] maximums = day21.maximumValuesOfSubArrays(array, 2);

        //Then
        assertThat(maximums, arrayContaining(8, 6, 4, 3, 8, 8, 7));
    }
}