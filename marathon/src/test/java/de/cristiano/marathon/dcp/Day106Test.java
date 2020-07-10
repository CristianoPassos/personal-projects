package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;

class Day106Test {

    private final Day106 day106 = new Day106();

    @Test
    void query_shouldSucceed() {
        //Given
        final String[] words = {"dog", "deer", "deal"};
        day106.ingest(words);

        //When
        final String[] result = day106.search("de");

        //Then
        assertThat(result, arrayContainingInAnyOrder("deer", "deal"));
    }
}
