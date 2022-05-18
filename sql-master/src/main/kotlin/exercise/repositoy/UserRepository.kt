package exercise.repositoy

import exercise.domain.User
import java.util.concurrent.ConcurrentHashMap

class UserRepository : Repository<Long, User>(User::class) {

    companion object {
        private val DATA = ConcurrentHashMap<Long, User>()
    }

    override fun clear() = clear(DATA)

    override fun findAll(): Collection<User> = findAll(DATA)

    override fun findAllOrderedByDesc(fieldName: String): List<User> = findAllOrderedByDesc(fieldName, DATA.values)

    fun saveAll(entities: List<User>): Collection<User> {
        entities.forEach {
            DATA[it.id] = it
        }

        val ids = entities.map { it.id }

        return DATA
            .filterKeys { ids.contains(it) }
            .values
    }

}