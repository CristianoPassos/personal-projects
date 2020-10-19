package de.cristiano.marathon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Top30JavaInterviewCodingTasksTest {

    private val top30JavaInterviewCodingTasks = Top30JavaInterviewCodingTasks()

    @Test
    fun exercise15() {
        // When
        val permutations = top30JavaInterviewCodingTasks.exercise15("ABC")

        //Then
        assertThat(permutations).hasSize(6)
    }

}