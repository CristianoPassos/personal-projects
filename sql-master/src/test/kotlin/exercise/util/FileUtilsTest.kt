package exercise.util

import exercise.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FileUtilsTest {

    @Test
    fun `Absolute Path should be found`() {
        // When
        val absolutePath = FileUtils.getAbsolutePath("/users.csv")

        // Then
        assertThat(absolutePath).isNotBlank
    }

    @Test
    fun `User should be loaded`() {
        // When
        val users = FileUtils.parseFile("/users.csv", User::class.java)

        // Then
        assertThat(users).hasSize(5)

        val userOne = users.first { it.id == 1L }
        assertThat(userOne.name).isEqualTo("andre")
        assertThat(userOne.email).isEqualTo("andre@bar.de")
    }
}