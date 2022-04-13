package de.cristiano.marathon.dcp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ArrayTest {

    private val array = Array()

    @Test
    fun productExceptSelf() {
        val answer = array.productExceptSelf(intArrayOf(2, 3, 4, 5))

        assertThat(answer).isEqualTo(intArrayOf(60, 40, 30, 24))
    }

    @Test
    fun productExceptSelfWithZero() {
        val answer = array.productExceptSelf(intArrayOf(2, 3, 0, 5))

        assertThat(answer).isEqualTo(intArrayOf(0, 0, 30, 0))
    }

    @Test
    fun minSortingWindow() {
        val answer = array.minSortingWindow(intArrayOf(3, 7, 5, 6, 9))

        assertThat(answer).isEqualTo(1 to 3)
    }
}
