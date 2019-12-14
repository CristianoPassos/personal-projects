package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.ArrayMatching.arrayContaining;

class Day104Test {

    private Day104 day104 = new Day104();

    @Test
    void breakUp_shouldSucceed() {
        //When
        final String[] phrases = day104.breakUp("the quick brown fox jumps over the lazy dog", 10);
        final String[] phrases2 = day104.breakUp2("the quick brown fox jumps over the lazy dog", 10);

        //Then
        final String[] expected = {"the quick", "brown fox", "jumps over", "the lazy", "dog"};

        assertThat(phrases, arrayContaining(expected));
        assertThat(phrases2, arrayContaining(expected));
    }
}