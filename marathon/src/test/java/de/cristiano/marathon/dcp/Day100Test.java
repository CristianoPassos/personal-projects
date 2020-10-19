package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class Day100Test {

    private Day100 day100 = new Day100();

    @Test
    void longestPalindromic_shouldSucceed() {
        //When
        final String longestPalindromic = day100.longestPalindromic("aaaaaabbbbbzzcccddcbb");

        //Then
        assertThat(longestPalindromic).isEqualTo("aaaccbbbbbbbccaaa");
    }
}
