package de.cristiano.api.service

import de.cristiano.api.domain.Instrument
import de.cristiano.api.domain.Quote
import de.cristiano.api.service.repository.QuoteRepository
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class QuoteService(private val repository: QuoteRepository, val instrumentService: InstrumentService) {

    fun getLastQuotes(instruments: List<Instrument>): List<Quote> =
        repository.findLastQuoteGroupByInstrumentIds(instruments.map { it.id })

    fun getQuotesByPeriod(instrumentId: Long, openingAt: Instant, closingAt: Instant): Set<Quote> =
        repository.findByInstrumentIdAndReceivedAtGreaterThanEqualAndReceivedAtLessThan(
            instrumentId,
            openingAt,
            closingAt
        )

    fun save(quote: Quote): Quote {
        val isin = quote.isin

        if (quote.id == 0L) {
            val instrument = instrumentService.findActiveByIsin(isin)
                ?: instrumentService.findLastActiveByIsin(isin)
                ?: error("No Instrument found for ISIN: $isin")

            quote.instrumentId = instrument.id
        }

        return repository.save(quote)
    }
}
