package br.com.exersync.dto.exceptions

data class ErrorMessageModel(
    val statusCode: Int,
    val message: String
)