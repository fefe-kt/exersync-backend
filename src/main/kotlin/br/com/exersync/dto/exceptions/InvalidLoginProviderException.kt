package br.com.exersync.dto.exceptions

import org.springframework.http.HttpStatus

class InvalidLoginProviderException :
    BaseExerSyncException("O provedor de login social é inválido.", HttpStatus.FORBIDDEN)