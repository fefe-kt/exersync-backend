package br.com.exersync.dto.response

data class AuthenticatedUserResponse(val accessToken: String, val refreshToken: String, val userResponse: UserResponse?)
