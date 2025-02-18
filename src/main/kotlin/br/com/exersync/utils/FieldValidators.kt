package br.com.exersync.utils

import br.com.exersync.dto.exceptions.InvalidEmailException
import br.com.exersync.dto.exceptions.InvalidPasswordException

fun String.validateEmail() : String{
    val isEmailValid = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$").matches(this)
    return if (isEmailValid) this else throw InvalidEmailException()
}

fun String.validatePassword() : String {
    val isPasswordValid = Regex("^(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{6,15}$").matches(this)
    return if (isPasswordValid) this else throw InvalidPasswordException()
}