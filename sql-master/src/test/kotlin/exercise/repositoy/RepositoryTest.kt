package exercise.repositoy

import exercise.domain.Ad
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RepositoryTest() {

    private val repository = AdRepository()

    @BeforeEach
    fun loadState() {
        repository.clear()
        repository.saveAll(
            listOf(
                Ad(4, "T4", "notNull"),
                Ad(3, "T3"),
                Ad(1, "T1"),
                Ad(2, "T2"),
            )
        )
    }

    @Test
    fun `Should order by field name`() {
        // When
        val ads = repository.findAllOrderedByDesc("title")

        // Then
        assertThat(ads[0].title).isEqualTo("T4")
        assertThat(ads[1].title).isEqualTo("T3")
        assertThat(ads[2].title).isEqualTo("T2")
        assertThat(ads[3].title).isEqualTo("T1")
    }


    @Test
    fun `Should work with empty dataset`() {
        // Given
        repository.clear()

        // When
        val ads = repository.findAllOrderedByDesc("title")

        // Then
        assertThat(ads).isEmpty()
    }

    @Test
    fun `Should throw exception for unknown field`() {
        // When
        val exception = assertThrows<NoSuchFieldError> {
            repository.findAllOrderedByDesc("unknownField")
        }

        // Then
        assertThat(exception.message).isEqualTo("Filed unknownField does not exist in Ad")
    }

    @Test
    fun `Should work for null properties`() {
        // When
        val ads = repository.findAllOrderedByDesc("nullProperty")

        // Then
        assertThat(ads[3].title).isEqualTo("T4")
    }
}