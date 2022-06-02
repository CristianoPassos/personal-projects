package exercise.service

import exercise.repositoy.AdRepository
import exercise.repositoy.UserRepository
import exercise.util.ReflectionUtils
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

class JoinService {

    private val repositories = mutableListOf(AdRepository(), UserRepository())

    fun <T : Any, X : Any, R : Any> innerJoin(
        leftClass: KClass<T>,
        leftColumn: String,
        rightClass: KClass<X>,
        rightColumn: String,
        resultClass: KClass<R>,
    ): Collection<R> {
        val leftTableField = ReflectionUtils.getField(leftColumn, leftClass)
        val rightTableField = ReflectionUtils.getField(rightColumn, rightClass)

        if (rightTableField.returnType != leftTableField.returnType)
            throw IllegalArgumentException("Columns $leftColumn - $rightColumn are not of the same type")

        val leftEntitiesByColumn = repositories.first { it.forEntity(leftClass) }
            .findAll()
            .groupBy { leftTableField.get(it as T) }

        val rightEntitiesByColumn = repositories.first { it.forEntity(rightClass) }
            .findAll()
            .groupBy { rightTableField.get(it as X) }

        val primaryConstructor = resultClass.primaryConstructor!!
        val leftEntityProperties = leftClass.declaredMemberProperties.associateBy { it.name }
        val rightEntityProperties = rightClass.declaredMemberProperties.associateBy { it.name }

        return leftEntitiesByColumn.mapNotNull { leftTable ->
            val rightEntities = rightEntitiesByColumn[leftTable.key]

            rightEntities?.map { rightEntity ->
                leftTable.value.map { leftEntity ->

                    val args = primaryConstructor.parameters.associateWith { parameter ->

                        leftEntityProperties[parameter.name]?.get(leftEntity as T)
                            ?: rightEntityProperties[parameter.name]?.get(rightEntity as X)
                    }

                    primaryConstructor.callBy(args)
                }
            }?.flatten()
        }.flatten()
    }
}

data class PurchaseExample(
    val adId: Long,
    val title: String,
    val nullProperty: String? = null,
    val userId: Long,
    var name: String,
    val email: String
)