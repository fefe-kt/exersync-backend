package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

sealed class UserFieldAlreadyExists(
    stackTrace: String, statusError: HttpStatus = HttpStatus.CONFLICT
) : BaseExerSyncException(stackTrace, statusError) {
    class EmailAlreadyExists : UserFieldAlreadyExists("Esse email já existe.")
    class UsernameAlreadyExists : UserFieldAlreadyExists("Esse username já existe.")
}