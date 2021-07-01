package de.cristiano.api.controller

import de.cristiano.api.controller.InstrumentsController.Companion.RESOURCE
import de.cristiano.api.controller.InstrumentsController.Companion.SUB_RESOURCE_CANDLESTICK
import de.cristiano.api.domain.Instrument
import de.cristiano.api.service.QuoteService
import de.cristiano.api.service.repository.InstrumentRepository
import de.cristiano.api.util.BaseIT
import de.cristiano.api.util.FileUtils.LIST_QUOTE_TYPE_REF
import de.cristiano.api.util.FileUtils.readAsObject
import de.cristiano.api.util.MutableClock
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus.SC_NOT_FOUND
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC


class InstrumentsCandlestickControllerIT : BaseIT() {

    @Autowired
    private lateinit var instrumentRepository: InstrumentRepository

    @Autowired
    private lateinit var quoteService: QuoteService

    @Autowired
    private lateinit var clock: MutableClock

    @Test
    fun `GET candlestick should return last 30 minutes`() {
        // Given
        clock.setInstant(LocalDateTime.of(2021, 7, 2, 12, 45, 0, 0).toInstant(UTC))

        instrumentRepository.save(Instrument(description = "HV26074381K2", isin = "HV26074381K2"))
        readAsObject("/db/quotes/HV26074381K2.json", LIST_QUOTE_TYPE_REF, objectMapper)
            .forEach { quoteService.save(it) }


        Given {
            pathParam("isin", "HV26074381K2")
        } When {
            get(RESOURCE + SUB_RESOURCE_CANDLESTICK)
        } Then {
            statusCode(SC_OK)

            body("candlesticks[0].opened_at", equalTo("2021-07-02T12:15:00Z"))
            body("candlesticks[0].open_price", equalTo(220.8712F))
            body("candlesticks[0].high_price", equalTo(1416.8376F))
            body("candlesticks[0].low_price", equalTo(220.8712F))
            body("candlesticks[0].closing_price", equalTo(1190.9834F))
            body("candlesticks[0].closed_at", equalTo("2021-07-02T12:16:00Z"))

            body("candlesticks[1].opened_at", equalTo("2021-07-02T12:16:00Z"))
            body("candlesticks[1].open_price", equalTo(541.4631F))
            body("candlesticks[1].high_price", equalTo(1512.2308F))
            body("candlesticks[1].low_price", equalTo(541.4631F))
            body("candlesticks[1].closing_price", equalTo(1512.2308F))
            body("candlesticks[1].closed_at", equalTo("2021-07-02T12:17:00Z"))
            //Previous candle reused
            body("candlesticks[2].opened_at", equalTo("2021-07-02T12:17:00Z"))
            body("candlesticks[2].open_price", equalTo(541.4631F))
            body("candlesticks[2].high_price", equalTo(1512.2308F))
            body("candlesticks[2].low_price", equalTo(541.4631F))
            body("candlesticks[2].closing_price", equalTo(1512.2308F))
            body("candlesticks[2].closed_at", equalTo("2021-07-02T12:18:00Z"))
            // Values from 2021-07-02T12:25
            body("candlesticks[14].opened_at", equalTo("2021-07-02T12:29:00Z"))
            body("candlesticks[14].open_price", equalTo(585.5F))
            body("candlesticks[14].high_price", equalTo(649.1637F))
            body("candlesticks[14].low_price", equalTo(233.97F))
            body("candlesticks[14].closing_price", equalTo(551.5278F))
            body("candlesticks[14].closed_at", equalTo("2021-07-02T12:30:00Z"))
            // Last candlestick
            body("candlesticks[29].opened_at", equalTo("2021-07-02T12:44:00Z"))
            body("candlesticks[29].open_price", equalTo(1039.5F))
            body("candlesticks[29].high_price", equalTo(1428.3333F))
            body("candlesticks[29].low_price", equalTo(54.3288F))
            body("candlesticks[29].closing_price", equalTo(54.3288F))
            body("candlesticks[29].closed_at", equalTo("2021-07-02T12:45:00Z"))

            body("candlesticks.size()", equalTo(30))
        }
    }


    @Test
    fun `GET candlestick when first candle is unavailable`() {
        // Given
        clock.setInstant(LocalDateTime.of(2021, 7, 2, 12, 16, 0, 0).toInstant(UTC))

        instrumentRepository.save(Instrument(description = "HV26074381K2", isin = "HV26074381K2"))
        readAsObject("/db/quotes/HV26074381K2.json", LIST_QUOTE_TYPE_REF, objectMapper)
            .forEach { quoteService.save(it) }

        Given {
            pathParam("isin", "HV26074381K2")
        } When {
            get(RESOURCE + SUB_RESOURCE_CANDLESTICK)
        } Then {
            statusCode(SC_OK)

            body("candlesticks[0].opened_at", equalTo("2021-07-02T12:15:00Z"))
            body("candlesticks[0].open_price", equalTo(220.8712F))
            body("candlesticks[0].high_price", equalTo(1416.8376F))
            body("candlesticks[0].low_price", equalTo(220.8712F))
            body("candlesticks[0].closing_price", equalTo(1190.9834F))
            body("candlesticks[0].closed_at", equalTo("2021-07-02T12:16:00Z"))

            body("candlesticks.size()", equalTo(1))
        }
    }

    @Test
    fun `GET candlestick should return empty when no quotes in last 30 minutes`() {
        // Given
        instrumentRepository.save(Instrument(description = "HV26074381K2", isin = "HV26074381K2"))

        Given {
            pathParam("isin", "HV26074381K2")
        } When {
            get(RESOURCE + SUB_RESOURCE_CANDLESTICK)
        } Then {
            statusCode(SC_OK)

            body("candlesticks.size()", equalTo(0))
        }
    }

    @Test
    fun `GET candlestick should return 404 for unknown ISIN`() {
        // Given
        Given {
            pathParam("isin", "HV26074381K2")
        } When {
            get(RESOURCE + SUB_RESOURCE_CANDLESTICK)
        } Then {
            statusCode(SC_NOT_FOUND)

            body("status", equalTo(SC_NOT_FOUND))
            body("title", equalTo("Not Found"))
            body("detail", equalTo("Instrument not found with ISIN: HV26074381K2"))
        }
    }
}
