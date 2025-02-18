package br.com.exersync.domain.repositories

import br.com.exersync.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface UserRepository : JpaRepository<UserEntity, Int>