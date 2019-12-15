package de.cristiano.marathon.daily;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day22Test {

    private Day22 day22 = new Day22();

    @Test
    void isBalanced_shouldSucceed() {
        //When
        final boolean isBalanced = day22.isBalanced("{([])}");

        //Then
        assertThat(isBalanced, is(true));
    }

    @Test
    void isNotBalanced_shouldSucceed() {
        //When
        final boolean isBalanced = day22.isBalanced("{([)}");

        //Then
        assertThat(isBalanced, is(false));
    }
}