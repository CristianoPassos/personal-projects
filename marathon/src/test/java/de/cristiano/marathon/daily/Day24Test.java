package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day24Test {

    private Day24 day24 = new Day24();

    @Test
    void longestPalindromic_shouldSucceed() {
        //When
        final String longestPalindromic = day24.longestPalindromic("aaaaaabbbbbzzcccddcbb");

        //Then
        assertThat(longestPalindromic, is("aaaccbbbbbbbccaaa"));
    }
}