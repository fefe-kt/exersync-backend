package br.com.exersync.dto.data

data class GoogleSocialLoginResponse(
    val email: String,
    val name: String,
    val picture: String?,
    val givenName: String,
    val exp: Long,
)