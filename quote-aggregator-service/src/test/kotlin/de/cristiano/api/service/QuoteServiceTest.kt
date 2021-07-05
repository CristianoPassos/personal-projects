package de.cristiano.api.service

import de.cristiano.api.domain.Instrument
import de.cristiano.api.domain.Quote
import de.cristiano.api.service.repository.InstrumentRepository
import de.cristiano.api.service.repository.QuoteRepository
import de.cristiano.api.util.BaseIT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.Instant
import java.util.concurrent.TimeUnit.MINUTES

class QuoteServiceIT : BaseIT() {

    @Autowired
    private lateinit var quoteService: QuoteService

    @Autowired
    private lateinit var quoteRepository: QuoteRepository

    @Autowired
    private lateinit var instrumentRepository: InstrumentRepository

    @Test
    fun `GET Quotes should return only values in range`() {
        // Given
        val isin = "HV26074381K2"
        val instrumentId = instrumentRepository.save(Instrument(description = isin, isin = isin)).id
        val now = Instant.now()
        val openingAt = now.minusSeconds(MINUTES.toSeconds(30))

        quoteService.save(
            Quote(
                isin = isin,
                price = BigDecimal("541.4631"),
                instrumentId = instrumentId,
                receivedAt = openingAt.minusSeconds(1)
            )
        )
        quoteService.save(
            Quote(
                isin = isin,
                price = BigDecimal("220.8712"),
                instrumentId = instrumentId,
                receivedAt = now.minusSeconds(1)
            )
        )
        quoteService.save(
            Quote(isin = isin, price = BigDecimal("1025.3969"), instrumentId = instrumentId, receivedAt = now)
        )

        // When
        val quotes = quoteService.getQuotesByPeriod(instrumentId, openingAt, now)

        // Then
        assertThat(quotes.size).isEqualTo(1)
        assertThat(quotes.first().price).isEqualByComparingTo("220.8712")
    }
}
