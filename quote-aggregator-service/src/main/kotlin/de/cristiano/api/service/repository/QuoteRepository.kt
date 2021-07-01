package de.cristiano.api.service.repository

import de.cristiano.api.domain.Quote
import org.jetbrains.annotations.TestOnly
import org.springframework.stereotype.Component
import java.math.BigDecimal.ZERO
import java.time.Instant
import java.util.TreeSet
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class QuoteRepository {

    companion object {
        val QUOTES = ConcurrentHashMap<Long, TreeSet<Quote>>()

        private val ID_GENERATOR = AtomicLong()
    }

    @TestOnly
    fun findAll(): List<Quote> = QUOTES.flatMap { it.value }

    fun findLastQuoteGroupByInstrumentIds(instrumentIds: List<Long>): List<Quote> =
        instrumentIds.mapNotNull { QUOTES[it]?.last() }
            .map { it.copy() }

    // Assume returned Entities do not have a reference.
    fun findByInstrumentIdAndReceivedAtGreaterThanEqualAndReceivedAtLessThan(
        instrumentId: Long,
        receivedAtGreaterThan: Instant,
        receivedAtLessThan: Instant
    ): Set<Quote> {
        val quotes = QUOTES[instrumentId] ?: return emptySet()

        val firstSearchQuote = Quote(price = ZERO, isin = "", receivedAt = receivedAtGreaterThan)
        val lastSearchQuote = Quote(price =  ZERO, isin = "", receivedAt = receivedAtLessThan)

        return quotes.subSet(firstSearchQuote, true, lastSearchQuote, false)
    }

    fun save(quote: Quote): Quote {
        if (quote.id == 0L) quote.id = ID_GENERATOR.incrementAndGet()

        val instrumentId = quote.instrumentId

        QUOTES[instrumentId] = QUOTES.getOrDefault(instrumentId, TreeSet<Quote>(compareBy { it.receivedAt }))
            .also { it.add(quote.copy()) }

        return quote
    }
}
