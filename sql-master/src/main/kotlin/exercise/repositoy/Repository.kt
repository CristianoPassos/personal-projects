package exercise.repositoy

import exercise.util.ReflectionUtils
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

abstract class Repository<X, T : Any>(private val clazz: KClass<T>) {

    fun <V : Any> forEntity(otherClass: KClass<V>) = clazz == otherClass

    abstract fun clear()
    protected fun clear(data: ConcurrentHashMap<X, T>) = data.clear()

    abstract fun findAll(): Collection<T>
    protected fun findAll(data: ConcurrentHashMap<X, T>): Collection<T> = data.values

    abstract fun findAllOrderedByDesc(fieldName: String): List<T>
    protected fun findAllOrderedByDesc(fieldName: String, dataSource: Collection<T>): List<T> {
        val field = ReflectionUtils.getField(fieldName, clazz)

        return dataSource.sortedWith(compareByDescending(nullsLast()) {
            field.get(it) as? Comparable<Any>
        })
    }

    /**
     * Generic Method for saving, not completed.
     * fun saveAll(entities: List<T>, data: ConcurrentHashMap<X, T>): Collection<Ad> {
    entities.forEach {
    data[it.adId] = it
    }

    val ids = entities.map { it.adId }

    return AdRepository.DATA
    .filterKeys { ids.contains(it) }
    .values
    }
     */

}
