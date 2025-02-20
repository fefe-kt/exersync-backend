package br.com.exersync.application.configuration

import br.com.exersync.application.configuration.properties.JwtProperties
import br.com.exersync.domain.repositories.UserRepository
import br.com.exersync.services.UserService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Configuration {

    @Bean
    @Primary
    fun userDetailsService(userRepository: UserRepository): UserDetailsService =
        UserService(userRepository, passwordEncoder())

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setPasswordEncoder(passwordEncoder())
            it.setUserDetailsService(userDetailsService(userRepository))
        }

    @Bean
    fun authenticationManager(authConfiguration: AuthenticationConfiguration) = authConfiguration.authenticationManager
}