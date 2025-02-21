package br.com.exersync.services.validators

import br.com.exersync.application.configuration.properties.ProviderProperties
import br.com.exersync.dto.data.CommonSocialLoginParams
import br.com.exersync.dto.data.GoogleSocialLoginResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service("google")
class GoogleSocialLoginValidator(private val webClient: WebClient, private val providerProperties: ProviderProperties) :
    ISocialLoginValidator("google") {
    override suspend fun invoke(token: String): CommonSocialLoginParams {
        val response =
            webClient.get().uri(providerProperties.google + token).retrieve().awaitBody<GoogleSocialLoginResponse>()
        
    }

}