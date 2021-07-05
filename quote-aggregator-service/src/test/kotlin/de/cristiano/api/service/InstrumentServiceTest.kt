package de.cristiano.api.service

import de.cristiano.api.domain.Instrument
import de.cristiano.api.util.BaseIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class InstrumentServiceIT : BaseIT() {

    @Autowired
    private lateinit var service: InstrumentService

    @Test
    fun `Should not allow more than one Instrument with same ISIN active`() {
        // Given
        val isin = "HV26074381K2"
        service.save(Instrument(description = "One", isin = isin))

        // When
        val exception = assertThrows<IllegalStateException> {
            service.save(Instrument(description = "Two", isin = isin))
        }

        assertThat(exception.message).isEqualTo("Instrument: One with ISIN: HV26074381K2 is active")
    }
}
