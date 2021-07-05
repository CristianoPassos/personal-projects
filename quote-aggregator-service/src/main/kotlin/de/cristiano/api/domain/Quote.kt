package de.cristiano.api.domain

import java.math.BigDecimal
import java.time.Instant
import java.time.Instant.now

// Maybe move ISIN to its own type
data class Quote(
    var id: Long = 0,
    var isin: String,
    var price: BigDecimal,
    var instrumentId: Long = 0,
    var receivedAt: Instant = now()
)
