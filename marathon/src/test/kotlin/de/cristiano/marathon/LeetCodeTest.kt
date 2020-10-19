package de.cristiano.marathon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LeetCodeTest {

    private val leetCode = LeetCode()

    @Test
    fun numberOfStepsTest() {
        assertThat(leetCode.numberOfSteps(14)).isEqualTo(6)
    }

    @Test
    fun uniqueMorseRepresentationsTest() {
        assertThat(leetCode.uniqueMorseRepresentations(arrayOf("gin", "zen", "gig", "msg"))).isEqualTo(2)
    }


    @Test
    fun isSymmetricTest() {
        assertThat(leetCode.isSymmetric(null)).isTrue()
    }
}
