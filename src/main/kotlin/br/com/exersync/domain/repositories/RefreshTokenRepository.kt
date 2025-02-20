package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.RefreshTokenEntity
import br.com.exersync.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface RefreshTokenRepository : JpaRepository<RefreshTokenEntity, Int> {
    fun getRefereceByUser(user: UserEntity): RefreshTokenEntity?
}