package de.cristiano.api.service.repository

import de.cristiano.api.domain.Instrument
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Component
class InstrumentRepository {

    companion object {
        val INSTRUMENTS = ConcurrentHashMap<Long, Instrument>()

        private val ID_GENERATOR = AtomicLong()
    }

    fun save(instrument: Instrument): Instrument {
        if (instrument.id == 0L) instrument.id = ID_GENERATOR.incrementAndGet()

        INSTRUMENTS[instrument.id] = instrument.copy()

        return instrument
    }

    // When you search without an index ;)
    fun findByIsin(isin: String): List<Instrument> = INSTRUMENTS.values
        .filter { it.isin.contentEquals(isin, true) }
        .map { it.copy() }

    fun findByDeletedAtNotNull(): List<Instrument> = INSTRUMENTS.values
        .filter { it.deletedAt == null }
        .map { it.copy() }

    fun findActiveByIsin(isin: String): Instrument? = findByIsin(isin).find { it.deletedAt == null }

    fun findFirstByIsinOrderByDeletedAtDesc(isin: String): Instrument? = findByIsin(isin)
        .maxWithOrNull(compareBy { it.deletedAt })
}
