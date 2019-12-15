package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day101Test {

    private final Day101 day101 = new Day101();

    @Test
    void originalSentence_shouldSucceed() {
        //Given
        final Set<String> dictionary = new HashSet<>();
        dictionary.add("bed");
        dictionary.add("bath");
        dictionary.add("bedbath");
        dictionary.add("and");
        dictionary.add("beyond");

        final String sentence = "bedbathandbeyond";

        //When
        final String originalSentence = day101.originalSentence(dictionary, sentence);

        //Then
        assertThat(originalSentence, is("bedbath and beyond"));
    }
}