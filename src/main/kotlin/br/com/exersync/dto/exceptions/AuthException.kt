package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

sealed class AuthException(override val message: String) : BaseExerSyncException(message, HttpStatus.FORBIDDEN) {
    class DisabledUserException : AuthException("Usuário desativado.")

    class BlockedUserException : AuthException("Usuário bloqueado.")

    class InvalidCredentialsException : AuthException("Credenciais inválidas.")
}