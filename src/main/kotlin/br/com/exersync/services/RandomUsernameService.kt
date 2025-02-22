package br.com.exersync.services

import br.com.exersync.domain.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextInt

@Service
class RandomUsernameService(private val userService: UserRepository) {

    fun createRandomUsername(name: String): String {
        val minSubstringLength = name.length / 2
        var usernameCandidate: String

        do { // min: fe -> max: 2 - 4
            val randomEndIndex = Random.nextInt(minSubstringLength..name.length)
            usernameCandidate = name.substring(0, randomEndIndex) + generateRandomSuffix()
        } while (userService.existsByUserName(usernameCandidate))

        return usernameCandidate
    }

    private fun generateRandomSuffix(): String = UUID.randomUUID().toString().substring(0..5)
}