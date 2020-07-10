package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day18Test {
    private Day18 day18 = new Day18();

    @Test
    void longestSubstringWithDistinctCharacters() {
        //When
        final String longestSubstringWithDistinctCharacters = day18.longestSubstringWithDistinctCharacters("abcba", 2);

        //Then
        assertThat(longestSubstringWithDistinctCharacters, is("bcb"));
    }
}
