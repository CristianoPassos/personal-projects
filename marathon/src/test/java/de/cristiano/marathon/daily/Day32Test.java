package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day32Test {

    private Day32 day32 = new Day32();

    @Test
    void shorten_shouldSucceed() {
        final String shorten = day32.shorten("http://www.google.com");

        assertThat(shorten, is("b"));
    }

    @Test
    void restore_shouldSucceed() {
        //Given
        final String shorten = day32.shorten("http://www.google.com");

        //When
        final String restore = day32.restore(shorten);

        assertThat(restore, is("http://www.google.com"));

    }
}