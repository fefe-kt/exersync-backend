package br.com.exersync.services.validators

import br.com.exersync.application.configuration.properties.ProviderProperties
import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.dto.data.GoogleSocialLoginResponse
import br.com.exersync.dto.exceptions.AuthException
import br.com.exersync.services.RandomUsernameService
import br.com.exersync.services.UserTokenService
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import java.time.Instant
import java.util.UUID

@Service("google")
class GoogleSocialLoginValidator(
    private val webClient: WebClient,
    private val providerProperties: ProviderProperties,
    private val randomUsernameService: RandomUsernameService
) : ISocialLoginValidator {
    override val provider: String = "google"

    override suspend fun invoke(token: String): UserEntity {
        val response =
            webClient
                .get()
                .uri(providerProperties.google + token)
                .retrieve()
                .awaitBody<GoogleSocialLoginResponse>()

        response.run {
            if (isTokenExpired(exp))
                throw AuthException.InvalidOAuthTokenException()

            return UserEntity(
                email = email,
                name = name,
                password = UUID.randomUUID().toString(),
                userName = randomUsernameService.createRandomUsername(name)
            )
        }
    }

    private fun isTokenExpired(expirationDate: Long) =
        Instant.ofEpochSecond(expirationDate).isBefore(Instant.now())

}