package br.com.exersync.application.configuration.properties

import jakarta.validation.constraints.NotBlank
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    @field:NotBlank val secret: String,
    @field:NotBlank val expiration: Long,
    @field:NotBlank val refreshTime: Long
)