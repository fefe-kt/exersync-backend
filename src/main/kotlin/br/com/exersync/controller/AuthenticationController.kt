package br.com.exersync.controller

import br.com.exersync.dto.request.UserRequest
import br.com.exersync.dto.request.authentication.RefreshRequest
import br.com.exersync.dto.request.authentication.SocialLoginRequest
import br.com.exersync.dto.request.authentication.UserLoginRequest
import br.com.exersync.dto.response.AuthenticatedUserResponse
import br.com.exersync.services.AuthenticationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
internal class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: UserLoginRequest): AuthenticatedUserResponse? =
        authenticationService.login(loginRequest)


    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signUpRequest: UserRequest): AuthenticatedUserResponse? =
        authenticationService.signup(signUpRequest)

    @PostMapping("/refresh")
    fun refresh(@Valid @RequestBody refreshRequest: RefreshRequest): AuthenticatedUserResponse =
        authenticationService.refreshToken(refreshRequest.refreshToken)

    @PostMapping("/social-login")
    fun socialLogin(@Valid @RequestBody socialLoginRequest: SocialLoginRequest): AuthenticatedUserResponse? =
}