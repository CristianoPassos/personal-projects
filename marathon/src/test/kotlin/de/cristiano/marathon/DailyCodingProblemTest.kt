package de.cristiano.marathon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.`in`
import org.junit.jupiter.api.Test

class DailyCodingProblemTest {
    private val dcp = DailyCodingProblem()


    @Test
    fun arraysIntersectionTest() {
        // When
        val intersection =
            dcp.arraysIntersection(intArrayOf(1, 2, 3, 4, 5), intArrayOf(1, 2, 5, 7, 9), intArrayOf(1, 3, 4, 5, 8))

        // Then
        assertThat(intersection).contains(0, 1)
        assertThat(intersection).contains(1, 5)
    }

    @Test
    fun palindromePairsTest() {
        val palindromes = dcp.palindromePairs(arrayOf("code", "edoc", "da", "d"))

        assertThat(palindromes).containsAll(listOf(0 to 1, 1 to 0, 2 to 3))
    }

    @Test
    fun printZigZagFromTest() {
        val result = dcp.printZigZagFrom("thisisazigzag", 4)

        assertThat(result).isEqualTo(
            """t     a     g   
 h   s z   a     
  i i   i z      
   s     g     """
        )
    }
}