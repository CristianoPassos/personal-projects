package exercise.service

import exercise.repositoy.AdRepository
import exercise.repositoy.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StartupServiceIT {
    private val service = StartupService()
    private val adRepository = AdRepository()
    private val userRepository = UserRepository()

    @Test
    fun `Should recover initial state`() {
        // When
        service.retrieveLocalState()

        // Then
        assertThat(adRepository.findAll()).hasSize(8)
        assertThat(userRepository.findAll()).hasSize(5)
    }
}