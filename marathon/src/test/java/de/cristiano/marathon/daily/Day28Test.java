package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class Day28Test {

    private final Day28Iterative day28Iterative = new Day28Iterative();

    @Test
    void justifyText_shouldSucceed() {
        //Given
        final String[] words = {"BigWordWithManyCharacters", "the", "quick", "RealBigWordMoreThanLineLength", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};

        //When
        final List<String> text = day28Iterative.justifyText(words, 16);

        //Then
        String[] expected = {
                "BigWordWithManyCharacters",
                "the        quick",
                "RealBigWordMoreThanLineLength",
                "brown  fox jumps",
                "over   the  lazy",
                "dog             "
        };

        assertThat(text, contains(expected));
    }
}