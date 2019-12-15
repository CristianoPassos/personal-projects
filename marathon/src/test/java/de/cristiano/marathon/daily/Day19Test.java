package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContainingInAnyOrder;

class Day19Test {

    private final Day19 day19 = new Day19();

    @Test
    void query_shouldSucceed() {
        //Given
        final String[] words = {"dog", "deer", "deal"};
        day19.ingest(words);

        //When
        final String[] result = day19.search("de");

        //Then
        assertThat(result, arrayContainingInAnyOrder("deer", "deal"));
    }
}