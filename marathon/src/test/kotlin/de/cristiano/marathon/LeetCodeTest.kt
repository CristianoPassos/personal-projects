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
        assertThat(leetCode.isSymmetric(null)).isTrue
    }

    @Test
    fun mostCommonWord() {
        assertThat(leetCode.mostCommonWord("Bob. hIt, baLl", arrayOf("bob", "hit")))
            .isEqualTo("ball")

        assertThat(
            leetCode.mostCommonWord(
                "Bob hit a ball, the hit BALL flew far after it was hit.",
                arrayOf("hit")
            )
        ).isEqualTo("ball")
    }

    @Test
    fun validParentheses() {
        assertThat(leetCode.isValid("([)]")).isFalse
    }

    @Test
    fun backspaceCompare() {
        assertThat(leetCode.backspaceCompare("ab##", "c#d#")).isTrue
    }

    @Test
    fun bubbleSort() {
        val array = intArrayOf(2, 67, 3, 4, 8, 6, 45, 1, 2, 3, 4, 5, 98)
        leetCode.bubbleSort(array)
        assertThat(array).containsExactly(1, 2, 2, 3, 3, 4, 4, 5, 6, 8, 45, 67, 98)
    }

    @Test
    fun maxTwoDigitNumberTest() {
        assertThat(leetCode.maxTwoDigitNumber("50552")).isEqualTo(55)
        assertThat(leetCode.maxTwoDigitNumber("10101")).isEqualTo(10)
        assertThat(leetCode.maxTwoDigitNumber("88")).isEqualTo(88)
    }


    @Test
    fun minTimeToVisitAllPointsTest() {
        assertThat(
            leetCode.minTimeToVisitAllPoints(
                arrayOf(
                    intArrayOf(3, 2),
                    intArrayOf(-2, 2)
                )
            )
        ).isEqualTo(5)

        assertThat(
            leetCode.minTimeToVisitAllPoints(
                arrayOf(
                    intArrayOf(1, 1),
                    intArrayOf(3, 4),
                    intArrayOf(-1, 0)
                )
            )
        ).isEqualTo(7)
    }

    @Test
    fun findDisappearedNumbersTest() {
        // Given
        val nums = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)

        // When
        val disappearedNumbers = leetCode.findDisappearedNumbers(nums)

        // Then
        assertThat(disappearedNumbers).contains(5, 6)
    }

    @Test
    fun flipGameTest() {
        // Given
        val game = "++++"

        // When
        val states = leetCode.flipGame(game)

        // Then
        assertThat(states).contains("--++", "+--+", "++--")
    }

    @Test
    fun flipAndInvertImageTest() {
        // Given
        val image = arrayOf(intArrayOf(1, 1, 0), intArrayOf(1, 0, 1), intArrayOf(0, 0, 0))

        // When
        val flippedAndInvertedImage = leetCode.flipAndInvertImage(image)

        // Then
        assertThat(flippedAndInvertedImage).isDeepEqualTo(
            arrayOf(
                intArrayOf(1, 0, 0),
                intArrayOf(0, 1, 0),
                intArrayOf(1, 1, 1)
            )
        )
    }


    @Test
    fun isHappyTest() {
        // When
        val happy = leetCode.isHappy(19)

        // Then
        assertThat(happy).isTrue
    }

}
