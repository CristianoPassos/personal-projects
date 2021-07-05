package de.cristiano.api.domain

import java.time.Instant


data class Instrument(var id: Long = 0, var description: String, var isin: String, var deletedAt: Instant? = null)
