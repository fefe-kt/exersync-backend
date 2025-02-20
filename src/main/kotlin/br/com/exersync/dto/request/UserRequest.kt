package br.com.exersync.dto.request

data class UserRequest(
    val name: String,
    val email: String,
    val userName: String,
    val password: String,
    val phone: String,
    val role: String
)