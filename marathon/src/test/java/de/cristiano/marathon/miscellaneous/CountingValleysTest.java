package de.cristiano.marathon.miscellaneous;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountingValleysTest {

    @Test
    public void test() {
        final int valleys = CountingValleys.countingValleys(8, "UDDDUDUU");
        assertEquals(1, valleys);
    }
}
