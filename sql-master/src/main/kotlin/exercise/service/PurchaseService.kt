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

        val rightEntitiesByColumn = adRepository.findAll().groupBy { rightTableField.get(it).toString() }

        val lefEntitiesByColumn = userRepository.findAll().groupBy { leftTableField.get(it).toString() }

        return rightEntitiesByColumn.mapNotNull { rightTable ->
            val leftTable = lefEntitiesByColumn[rightTable.key]

            if (leftTable != null) {
                return rightTable.value.map { rightEntity ->
                    leftTable.map { leftEntity ->
                        Purchase(
                            name = leftEntity.name,
                            adId = rightEntity.adId,
                            email = leftEntity.email,
                            title = rightEntity.title,
                            userId = leftEntity.userId,
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