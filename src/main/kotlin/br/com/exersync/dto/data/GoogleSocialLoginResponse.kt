package br.com.exersync.dto.data

data class GoogleSocialLoginResponse(
    val aud: String,
    val sub: String,
    val email: String,
    val name: String,
    val picture: String?,
    val locale: String?
)