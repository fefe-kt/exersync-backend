package br.com.exersync.services.validators

import br.com.exersync.domain.entities.UserEntity
import org.springframework.stereotype.Component


interface ISocialLoginValidator {
    val provider: String
    suspend operator fun invoke(token: String): UserEntity
}