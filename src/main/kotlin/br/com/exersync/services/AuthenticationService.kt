package br.com.exersync.services

import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.dto.exceptions.AuthException
import br.com.exersync.dto.mappers.toEntity
import br.com.exersync.dto.mappers.toResponse
import br.com.exersync.dto.request.UserRequest
import br.com.exersync.dto.request.authentication.SocialLoginRequest
import br.com.exersync.dto.request.authentication.UserLoginRequest
import br.com.exersync.dto.response.AuthenticatedUserResponse
import br.com.exersync.services.usecases.SocialLoginUseCase
import jakarta.transaction.Transactional
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userTokenService: UserTokenService,
    private val userService: UserService,
    private val socialLoginUseCase: SocialLoginUseCase
) {
    @Transactional
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
            return authenticateUser(loginRequest.email)
        }
        return null
    }

    @Transactional
    fun signup(userRequest: UserRequest): AuthenticatedUserResponse? {
        runCatching {
            userService.createUser(userRequest.toEntity())
        }.onFailure {
            throw it
        }.onSuccess {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    userRequest.email,
                    userRequest.password
                )
            )
            return authenticateUser(it.email, it)
        }
        return null
    }

    @Transactional
    fun refreshToken(token: String): AuthenticatedUserResponse {
        if (userTokenService.isValid(token, userService.loadUserByUsername(token))) {
            return authenticateUser(tokenSubject = token)
        } else {
            throw AuthException.ExpiredSessionException()
        }
    }

    suspend fun socialLogin(socialLoginRequest: SocialLoginRequest): AuthenticatedUserResponse = coroutineScope {
        val authenticateSocialLogin = async { socialLoginUseCase(socialLoginRequest) }.await()
        val user = userService.findByEmail(authenticateSocialLogin.email) ?:
            userService.createUser(authenticateSocialLogin)

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.email,
                user.password
            )
        )
        return@coroutineScope authenticateUser(user.email, user)
    }

    private fun authenticateUser(tokenSubject: String, userEntity: UserEntity? = null): AuthenticatedUserResponse {
        val userDetails = userService.loadUserByUsername(tokenSubject)
        val (token, refreshableToken) = userTokenService.generateTokens(userDetails)

        userEntity?.let {
            userTokenService.setRefreshToken(refreshableToken, it)
        } ?: userService.findByEmail(tokenSubject)?.let {
            userTokenService.setRefreshToken(refreshableToken, it)
        }

        return AuthenticatedUserResponse(
            token,
            refreshableToken,
            (userEntity ?: userService.findByEmail(tokenSubject))?.toResponse()
        )
    }

    private fun handleErrors(exception: AuthenticationException): Exception = when (exception) {
        is DisabledException -> AuthException.DisabledUserException()
        is LockedException -> AuthException.BlockedUserException()
        is BadCredentialsException -> AuthException.InvalidCredentialsException()
        else -> exception
    }
}