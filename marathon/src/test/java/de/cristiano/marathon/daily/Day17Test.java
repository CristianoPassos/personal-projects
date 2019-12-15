package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    private Day17 day17 = new Day17();

    @Test
    void encode_shouldSucceed() {
        //When
        final String encoded = day17.encode("AAAABBBCCDAA");

        //Then
        assertThat(encoded, is("A4B3C2D1A2"));
    }

    @Test
    void decode_shouldSucceed(){
        //When
        final String decoded = day17.decode("A4B3C2D1A2");

        //Then
        assertThat(decoded, is("AAAABBBCCDAA"));
    }
}