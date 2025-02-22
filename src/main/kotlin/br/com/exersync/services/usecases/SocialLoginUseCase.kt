package br.com.exersync.services.usecases

import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.dto.exceptions.AuthException
import br.com.exersync.dto.exceptions.InvalidLoginProviderException
import br.com.exersync.dto.request.authentication.SocialLoginRequest
import br.com.exersync.services.validators.GoogleSocialLoginValidator
import br.com.exersync.services.validators.ISocialLoginValidator
import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class SocialLoginUseCase(private val validators: Set<ISocialLoginValidator>) {
    suspend operator fun invoke(socialLoginRequest: SocialLoginRequest): UserEntity {
        validators.find { it.provider == socialLoginRequest.provider }?.let {
            try {
                return it(socialLoginRequest.token)
            } catch (e: Exception) {
                throw AuthException.InvalidOAuthTokenException()
            }
        } ?: throw InvalidLoginProviderException()
    }
}