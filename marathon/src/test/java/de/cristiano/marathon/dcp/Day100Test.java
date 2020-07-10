package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day100Test {

    private Day100 day100 = new Day100();

    @Test
    void longestPalindromic_shouldSucceed() {
        //When
        final String longestPalindromic = day100.longestPalindromic("aaaaaabbbbbzzcccddcbb");

        //Then
        assertThat(longestPalindromic, is("aaaccbbbbbbbccaaa"));
    }
}
