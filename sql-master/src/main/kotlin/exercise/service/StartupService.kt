package exercise.service

import exercise.domain.Ad
import exercise.domain.User
import exercise.repositoy.AdRepository
import exercise.repositoy.UserRepository
import exercise.util.FileUtils.parseFile

class StartupService {

    private val adRepository = AdRepository()
    private val userRepository = UserRepository()

    fun retrieveLocalState() {
        val usersDto = parseFile("/users.csv", UserDto::class.java)
        val purchasesDto = parseFile("/purchases.csv", PurchasesDto::class.java)

        adRepository.saveAll(purchasesDto.map { Ad(it.adId, it.title) })
        userRepository.saveAll(usersDto.map { User(it.userId, it.name, it.email) })
    }

}

data class UserDto(val userId: Long, var name: String, val email: String)
data class PurchasesDto(val adId: Long, val title: String, val userId: Long)
