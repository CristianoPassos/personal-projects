package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day34Test {

    private Day34 day34 = new Day34();

    @Test
    void breakUp_shouldSucceed() {
        //When
        final String[] phrases = day34.breakUp("the quick brown fox jumps over the lazy dog", 10);
        final String[] phrases2 = day34.breakUp2("the quick brown fox jumps over the lazy dog", 10);

        //Then
        final String[] expected = {"the quick", "brown fox", "jumps over", "the lazy", "dog"};

        assertThat(phrases, arrayContaining(expected));
        assertThat(phrases2, arrayContaining(expected));
    }
}