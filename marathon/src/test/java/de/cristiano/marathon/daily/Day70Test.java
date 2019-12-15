package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;

class Day70Test {

    private final Day70 day70 = new Day70();

    @Test
    void query_shouldSucceed() {
        //Given
        final String[] words = {"dog", "deer", "deal"};
        day70.ingest(words);

        //When
        final String[] result = day70.search("de");

        //Then
        assertThat(result, arrayContainingInAnyOrder("deer", "deal"));
    }
}