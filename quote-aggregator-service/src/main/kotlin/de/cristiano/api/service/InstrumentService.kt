package de.cristiano.api.service

import de.cristiano.api.domain.Instrument
import de.cristiano.api.service.repository.InstrumentRepository
import org.springframework.stereotype.Service
import java.time.Instant.now


@Service
class InstrumentService(private val repository: InstrumentRepository) {

    fun findActive(): List<Instrument> = repository.findByDeletedAtNotNull()

    fun findActiveByIsin(isin: String): Instrument? = repository.findActiveByIsin(isin)

    fun findLastActiveByIsin(isin: String): Instrument? = repository.findFirstByIsinOrderByDeletedAtDesc(isin)

    fun save(instrument: Instrument): Instrument {
        val isin = instrument.isin
        val activeByIsin = findActiveByIsin(isin)

        if (activeByIsin != null && activeByIsin.id != instrument.id) {
            error("Instrument: ${activeByIsin.description} with ISIN: $isin is active")
        }

        return repository.save(instrument)
    }

    fun deleteByIsin(isin: String) {
        val instrument = repository.findActiveByIsin(isin) ?: return

        instrument.deletedAt = now()

        repository.save(instrument)
    }

}
