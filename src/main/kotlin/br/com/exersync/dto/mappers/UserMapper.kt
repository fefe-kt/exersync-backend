package br.com.exersync.dto.mappers

import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.domain.enums.RoleEnum
import br.com.exersync.dto.request.UserRequest
import br.com.exersync.dto.response.UserResponse
import br.com.exersync.utils.validateEmail
import br.com.exersync.utils.validatePassword

fun UserRequest.toEntity() = UserEntity(
    name = name,
    email = email.validateEmail(),
    password = password.validatePassword(),
    phone = phone,
    profilePictureUrl = null,
    role = RoleEnum.valueOf(role)
)

fun UserEntity.toResponse() = UserResponse(
    id = id,
    name = name,
    profilePictureUrl = profilePictureUrl,
    role = role
)