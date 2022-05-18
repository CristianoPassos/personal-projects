package exercise.service

import exercise.domain.Ad
import exercise.domain.User
import exercise.repositoy.AdRepository
import exercise.repositoy.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PurchaseServiceTest {

    private val service = PurchaseService()

    private val adRepository = AdRepository()
    private val userRepository = UserRepository()

    @BeforeEach
    fun beforeEach() {
        adRepository.clear()
        userRepository.clear()

        adRepository.saveAll(
            listOf(
                Ad(1, "Title1"),
                Ad(2, "Title2")
            )
        )
        userRepository.saveAll(
            listOf(
                User(1, "Title1", "user1@email"),
                User(2, "Title1", "user2@email")
            )
        )
    }

    @Test
    fun `Should INNER JOIN based on column name`() {
        // When
        val innerJoin = service.findAllUserPurchasesByField("title", "name")

        // Then
        assertThat(innerJoin).hasSize(2)

        val user1 = innerJoin.first { it.adId == 1L }
        assertThat(user1.adId).isEqualTo(1L)
        assertThat(user1.userId).isEqualTo(1L)
        assertThat(user1.name).isEqualTo("Title1")
        assertThat(user1.title).isEqualTo("Title1")
        assertThat(user1.email).isEqualTo("user1@email")
    }

    @Test
    fun `Should not INNER JOIN different columns types`() {
        // When
        val exception = assertThrows<IllegalArgumentException> {
            service.findAllUserPurchasesByField("title", "userId")
        }

        // Then
        assertThat(exception.message).isEqualTo("Columns title - userId are not of the same type")
    }
}