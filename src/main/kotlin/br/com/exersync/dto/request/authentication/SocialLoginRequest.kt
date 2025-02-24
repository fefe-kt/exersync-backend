package br.com.exersync.dto.request.authentication

data class SocialLoginRequest(
    val provider: String,
    val token: String
)