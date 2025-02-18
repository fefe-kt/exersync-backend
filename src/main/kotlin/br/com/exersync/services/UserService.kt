package br.com.exersync.services

import br.com.exersync.domain.entities.UserEntity
import br.com.exersync.domain.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun createUser(userEntity: UserEntity) : UserEntity{
       return userRepository.save(userEntity)
    }
}