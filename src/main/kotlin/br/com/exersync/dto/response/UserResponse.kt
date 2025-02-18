package br.com.exersync.dto.response

import br.com.exersync.domain.enums.RoleEnum

data class UserResponse(
    val id: Long,
    val name: String,
    val profilePictureUrl: String? = null,
    val role: RoleEnum
)