package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

class InvalidPasswordException : BaseExerSyncException(
    "A senha deve conter:" +
            "- Entre 6 e 15 caracteres" +
            "- Pelo menos uma letra maiúscula" +
            "- Pelo menos um número",
    HttpStatus.NOT_ACCEPTABLE
)