package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day34Test {

    private final Day34Iterative day34 = new Day34Iterative();


    @Test
    void minPalindrome_shouldSucceed() {
        //When
        final String minPalindromeRace = day34.minPalindrome("race");
        final String minPalindromeGoogle = day34.minPalindrome("google");

        //Then
        assertThat(minPalindromeRace, is("acereca"));
        assertThat(minPalindromeGoogle, is("egloolge"));
    }
}
