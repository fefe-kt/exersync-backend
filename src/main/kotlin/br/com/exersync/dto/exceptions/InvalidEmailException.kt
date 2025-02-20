package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

class InvalidEmailException : BaseExerSyncException("O email digitado é inválido.", HttpStatus.NOT_ACCEPTABLE)