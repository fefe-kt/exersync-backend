package br.com.exersync.services

import br.com.exersync.dto.exceptions.AuthException
import br.com.exersync.dto.mappers.toEntity
import br.com.exersync.dto.request.UserRequest
import br.com.exersync.dto.request.authentication.UserLoginRequest
import br.com.exersync.dto.response.AuthenticatedUserResponse
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userTokenService: UserTokenService,
    private val userService: UserService
) {
    fun login(loginRequest: UserLoginRequest): AuthenticatedUserResponse? {
        runCatching {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    loginRequest.email,
                    loginRequest.password
                )
            )
        }.onFailure {
            throw handleErrors(it as AuthenticationException)
        }.onSuccess {
            val user = userService.loadUserByUsername(loginRequest.email)
            val token = userTokenService.generateToken(user)
            return AuthenticatedUserResponse(token, "")
        }
        return null
    }

    fun signup(userRequest: UserRequest): AuthenticatedUserResponse? {
        runCatching {
            userService.createUser(userRequest.toEntity())
        }.onFailure {
            throw it
        }.onSuccess {
            val user = userService.loadUserByUsername(it.email)
            val token = userTokenService.generateToken(user)
            return AuthenticatedUserResponse(token, "")
        }
        return null
    }

    private fun handleErrors(exception: AuthenticationException): Exception = when (exception) {
        is DisabledException -> AuthException.DisabledUserException()
        is LockedException -> AuthException.BlockedUserException()
        is BadCredentialsException -> AuthException.InvalidCredentialsException()
        else -> exception
    }
}