package br.com.exersync.controller.advice

import br.com.exersync.dto.exceptions.BaseExerSyncException
import br.com.exersync.dto.exceptions.ErrorMessageModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {
    @ExceptionHandler
    fun handleBaseExerSyncException(ex: BaseExerSyncException): ResponseEntity<ErrorMessageModel> {
        val errorMessageModel = ErrorMessageModel(ex.statusError.value(), message = ex.message.orEmpty())

        return ResponseEntity(errorMessageModel, ex.statusError)
    }
}