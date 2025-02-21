package br.com.exersync.services.validators

import br.com.exersync.dto.data.CommonSocialLoginParams
import org.springframework.stereotype.Component


abstract class ISocialLoginValidator(val provider: String) {
    abstract suspend operator fun invoke(token: String): CommonSocialLoginParams
}