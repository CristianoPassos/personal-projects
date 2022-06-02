package exercise.repositoy

import exercise.domain.Ad
import java.util.concurrent.ConcurrentHashMap

class AdRepository : Repository<Long, Ad>(Ad::class) {

    companion object {
        private val DATA = ConcurrentHashMap<Long, Ad>()
    }

    override fun clear() = clear(DATA)

    override fun findAll(): Collection<Ad> = findAll(DATA)

    override fun findAllOrderedByDesc(fieldName: String): List<Ad> = findAllOrderedByDesc(fieldName, DATA.values)

    fun saveAll(entities: List<Ad>): Collection<Ad> {
        entities.forEach {
            DATA[it.adId] = it
        }

        val ids = entities.map { it.adId }

        return DATA
            .filterKeys { ids.contains(it) }
            .values
    }
}