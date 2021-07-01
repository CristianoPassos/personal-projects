package de.cristiano.api.controller

import de.cristiano.api.config.NotFoundException
import de.cristiano.api.controller.InstrumentsController.Companion.RESOURCE
import de.cristiano.api.domain.Quote
import de.cristiano.api.service.InstrumentService
import de.cristiano.api.service.QuoteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.Instant.now
import java.time.temporal.ChronoUnit.MINUTES

@RestController
@RequestMapping(RESOURCE)
class InstrumentsController(
    private val service: InstrumentService,
    private val quoteService: QuoteService,
    private val clock: Clock
) {

    companion object {
        const val RESOURCE = "/instruments"
        const val SUB_RESOURCE_CANDLESTICK = "/{isin}/candlestick"

        private const val ONE_MINUTE = 60L
    }

    @GetMapping
    fun getInstruments(): InstrumentResponse {
        val activeInstruments = service.findActive()
        val lastQuoteByIsin = quoteService.getLastQuotes(activeInstruments)
            .associateBy { it.isin }

        return activeInstruments
            .map { InstrumentDto(it.description, it.isin, lastQuoteByIsin[it.isin]?.price) }
            .let { InstrumentResponse(it) }
    }

    // Data visualization should be done by the client
    @GetMapping(SUB_RESOURCE_CANDLESTICK)
    fun getInstrumentCandlestick(@PathVariable isin: String): CandlestickResponse {
        val instrument = service.findActiveByIsin(isin)
            ?: service.findLastActiveByIsin(isin)
            ?: throw NotFoundException("Instrument not found with ISIN: $isin")

        val closingAt = now(clock).truncatedTo(MINUTES)
        val openingAt = closingAt.minusSeconds(ONE_MINUTE.times(30))

        return quoteService.getQuotesByPeriod(instrument.id, openingAt, closingAt)
            .groupBy { it.receivedAt.truncatedTo(MINUTES) }
            .let { aggregateToCandlesticks(it, openingAt, closingAt) }
            .let { CandlestickResponse(it) }
    }

    // Quote: "If there weren't any prices received for a specific candle, values from the previous candle are reused."
    // What should happen when the first one is missing?
    private fun aggregateToCandlesticks(
        quotesByOpenTime: Map<Instant, List<Quote>>,
        openingAt: Instant,
        closingAt: Instant,
    ): List<CandlestickDto> {
        var candleOpenTime = openingAt
        val candlesticks = mutableListOf<CandlestickDto>()
        var candlestick: CandlestickDto? = null

        while (candleOpenTime < closingAt) {
            val candleCloseTime = candleOpenTime.plusSeconds(ONE_MINUTE)
            val candleQuotes = quotesByOpenTime[candleOpenTime]

            candlestick = candleQuotes?.let { quotesToCandlestick(it, candleOpenTime, candleCloseTime) }
                ?: candlestick?.copy(openedAt = candleOpenTime, closedAt = candleCloseTime)

            candlestick?.also { candlesticks.add(it) }

            candleOpenTime = candleCloseTime
        }

        return candlesticks
    }


    private fun quotesToCandlestick(quotes: List<Quote>, openedAt: Instant, closedAt: Instant): CandlestickDto {
        // No minBy :(
        val openQuote = quotes.minByOrNull { it.receivedAt }!!
        val highestQuote = quotes.maxByOrNull { it.price }!!
        val lowestQuote = quotes.minByOrNull { it.price }!!
        val closingQuote = quotes.maxByOrNull { it.receivedAt }!!

        return CandlestickDto(
            openedAt,
            openQuote.price,
            highestQuote.price,
            lowestQuote.price,
            closingQuote.price,
            closedAt
        )
    }
}

data class InstrumentResponse(val instruments: List<InstrumentDto>)

data class InstrumentDto(
    val description: String,
    val isin: String,
    val lastPrice: BigDecimal?
)

data class CandlestickResponse(val candlesticks: List<CandlestickDto>)

data class CandlestickDto(
    val openedAt: Instant,
    val openPrice: BigDecimal,
    val highPrice: BigDecimal,
    val lowPrice: BigDecimal,
    val closingPrice: BigDecimal,
    val closedAt: Instant,
)
