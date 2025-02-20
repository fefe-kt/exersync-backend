package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

open class BaseExerSyncException(
    override val message: String?,
    open val statusError: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
) : Exception(message)