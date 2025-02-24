package br.com.exersync.application.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "providers")
data class ProviderProperties(
    val google: String
)