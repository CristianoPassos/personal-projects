package de.cristiano.challenges;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseStringTest {

    private ReverseString reverseString= new ReverseString();

    @Test
    void solution() {
        //When
        final var solution = reverseString.solution("Hi, My name is:");

        //Then
        assertEquals(":si eman yM ,iH", solution);
    }
}