package de.cristiano.marathon.dcp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    private final Day12 day12 = new Day12(10);

    @Test
    void sequence_shouldSucceed() {
        day12.push(1);
        day12.push(7);
        assertEquals(7, day12.max());
        day12.push(12);
        day12.push(20);
        assertEquals(20, day12.max());
        assertEquals(20, day12.pop());
        day12.push(2);
        assertEquals(12, day12.max());
        assertEquals(2, day12.pop());
        assertEquals(12, day12.pop());
        assertEquals(7, day12.max());
    }
}
