package exercise.service

import exercise.domain.Ad
import exercise.domain.User
import exercise.repositoy.AdRepository
import exercise.repositoy.UserRepository
import exercise.util.ReflectionUtils

class PurchaseService {

    private val adRepository = AdRepository()
    private val userRepository = UserRepository()

    fun findAllUserPurchasesByField(
        adColumn: String,
        userColumn: String,
    ): Collection<Purchase> {
        val rightTableField = ReflectionUtils.getField(adColumn, Ad::class)
        val leftTableField = ReflectionUtils.getField(userColumn, User::class)

        if (rightTableField.returnType != leftTableField.returnType)
            throw IllegalArgumentException("Columns $adColumn - $userColumn are not of the same type")

        val leftEntitiesByColumn = adRepository.findAll().groupBy { rightTableField.get(it).toString() }
        val rightEntitiesByColumn = userRepository.findAll().groupBy { leftTableField.get(it).toString() }

        return leftEntitiesByColumn.mapNotNull { rightTable ->
            val leftTable = rightEntitiesByColumn[rightTable.key]

            if (leftTable != null) {
                return rightTable.value.map { rightEntity ->
                    leftTable.map { leftEntity ->
                        Purchase(
                            name = leftEntity.name,
                            adId = rightEntity.id,
                            email = leftEntity.email,
                            title = rightEntity.title,
                            userId = leftEntity.id,
                        )
                    }
                }.flatten()
            }
            null
        }.flatten()
    }
}

data class Purchase(
    val adId: Long,
    val userId: Long,
    var name: String,
    val email: String,
    val title: String
)