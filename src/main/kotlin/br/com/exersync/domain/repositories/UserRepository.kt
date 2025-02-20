package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {

    fun findByEmail(email: String?): UserEntity?
    fun existsByEmail(email: String): Boolean

    fun existsByUserName(userName: String): Boolean
}