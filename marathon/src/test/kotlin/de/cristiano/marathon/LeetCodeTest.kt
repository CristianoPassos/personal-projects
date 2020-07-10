package de.cristiano.marathon

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LeetCodeTest {

    @Test
    fun numberOfStepsTest() {
        assertEquals(6, numberOfSteps(14))
    }

    @Test
    fun uniqueMorseRepresentationsTest() {
        assertEquals(2, uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg")))
    }
}
